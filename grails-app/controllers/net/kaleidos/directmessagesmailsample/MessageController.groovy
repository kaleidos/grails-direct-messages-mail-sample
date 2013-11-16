package net.kaleidos.directmessagesmailsample
import net.kaleidos.directmessages.ThreadMessageService
import net.kaleidos.directmessages.Message
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class MessageController {
    def mailMessagingService
    def threadMessageService
    def springSecurityService
    def grailsApplication

    //This needs the grails mail plugin
    def mailService

    static Integer ITEMS_BY_PAGE = 20


    def inbox() {
        def currentUser = springSecurityService.currentUser
        def offset = 0
        def sort
        def order = 'desc'


        if (params.sort == 'fromName') {
            sort = 'fromName'
        } else if (params.sort == 'subject') {
            sort = 'subject'
        } else if (params.sort == 'dateCreated') {
            sort = 'dateCreated'
        } else {
            //If the user hadn't ask for a sort, make a special sort with the unreaded messages first
            sort = 'readed'
        }

        if (params.order == 'asc') {
            order = 'asc'
        }

        if (params.offset) {
            try {
                offset = Integer.parseInt(params.offset)
            } catch (Exception e) {
                offset = 0
            }
        }

        def result = threadMessageService.getReceivedByThread(currentUser.id, offset, ITEMS_BY_PAGE, sort, order)
        render view:'inbox', model:[user:currentUser, messages:result.messages, totalNum:result.totalNum, unreadedNum:result.unreadedNum, max:ITEMS_BY_PAGE, sort:sort, order:order]
    }

    def sent() {
        def currentUser = springSecurityService.currentUser
        def sort
        def order = 'desc'


        if (params.sort == 'toName') {
            sort = 'toName'
        } else if (params.sort == 'subject') {
            sort = 'subject'
        } else {
            sort = 'dateCreated'
        }

        if (params.order == 'asc') {
            order = 'asc'
        }

        def result = threadMessageService.getSentByThread(currentUser.id, 0, -1, sort, order)


        render view:'sent', model:[user:currentUser, messages:result.messages, totalNum:result.totalNum, sort:sort, order:order]
    }

    def view() {
        def currentUser = springSecurityService.currentUser
        def message = Message.get(params.messageId)
        //If the message exists and the user can view the message
        if ((message) &&
            ((message.fromId == currentUser.id) || (message.toId == currentUser.id))) {
                def messages = threadMessageService.findAllMessagesOnThread(message)

                //Mark as readed
                messages.each {
                    if (it.toId == currentUser.id) {
                        it.readed = true
                        it.save()
                    }
                }

                def otherUser = message.fromId == currentUser.id?User.get(message.toId):User.get(message.fromId)
                render view:'thread', model:[user:currentUser, messages:messages, subject:message.subject, otherUser:otherUser]
        } else {
            redirect mapping: 'inbox'
        }
    }

    def newMessage() {
        def currentUser = springSecurityService.currentUser
        def otherUser = User.get(params.toId)
        if (otherUser) {
            render view:'newMessage', model:[user:currentUser, otherUser:otherUser]
        } else {
            redirect mapping: 'inbox'
        }
    }
    def saveNewMessage() {
        def currentUser = springSecurityService.currentUser
        def toUser = User.get(params.toId)

        if (toUser) {
            def userBlock = UserBlock.findByUserAndUserBlocked(toUser, currentUser)
            if (!userBlock) {
                if (params.subject && params.text && params.text.size()<=5000) {
                    mailMessagingService.sendMessage(currentUser, toUser, params.text, params.subject)
                    flash.message = message(code: 'thread.success')
                } else {
                    flash.error = message(code: 'thread.error')
                }
            } else {
                flash.error = message(code: 'block.error', args:[toUser.name])
            }
        }
        redirect mapping: 'inbox'
    }

    def userList() {
        def currentUser = springSecurityService.currentUser
        //Get user role, asumming one role per user
        def role = UserRole.findByUser(currentUser).role
        //Get a list of users with a diferent role that the currentUser
        def list = UserRole.createCriteria().list{
            ne 'role', role
            projections {
                property 'user'
            }
        }
        render view:'userList', model:[user:currentUser, userList:list]
    }

    def deleteMessages() {
        def currentUser = springSecurityService.currentUser
        def ids = params.list('deleteId')
        ids.each {id->
            try {
                Message m = Message.get(Long.parseLong(id))
                if (m) {
                    threadMessageService.deleteMessagesOnThread(currentUser.id, m)
                }

            }catch (NumberFormatException e){
                //Ignore
            }
        }


        flash.message = message(code: 'inbox.delete.success')
        redirect mapping: 'inbox'

    }

    def reportUser() {
        def currentUser = springSecurityService.currentUser
        def admin = User.get(grailsApplication.config.admin_id)
        def otherUser = User.get(params.userId)

        if (admin && otherUser && currentUser != otherUser) {
            def text = "${message(code: 'report.text')} ${otherUser.name} (${otherUser.id})"
            mailMessagingService.sendMessage(currentUser, admin, text, message(code: 'report.subject'))

            //This need the grails mail plugin
            if (mailService) {
                mailService.sendMail{
                    to admin.username
                    from currentUser.username
                    subject message(code: 'report.subject')
                    body text
                }
            }

            flash.message = message(code: 'report.success')
        }
        redirect mapping: 'inbox'

    }


    def blockUser() {
        def currentUser = springSecurityService.currentUser
        def otherUser = User.get(params.userId)

        if (otherUser && currentUser != otherUser) {
            def userBlock = UserBlock.findByUserAndUserBlocked(currentUser, otherUser)
            if (!userBlock) {
                userBlock = new UserBlock(user:currentUser, userBlocked: otherUser)
                userBlock.save()
            }
            flash.message = message(code: 'block.success', args:[otherUser.name])
        }
        redirect mapping: 'inbox'
    }

}
