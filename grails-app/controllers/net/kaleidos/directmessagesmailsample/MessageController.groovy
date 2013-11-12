package net.kaleidos.directmessagesmailsample
import net.kaleidos.directmessages.DirectMessageService
import net.kaleidos.directmessages.Message
import grails.plugins.springsecurity.Secured

@Secured(['IS_AUTHENTICATED_REMEMBERED'])
class MessageController {
    def directMessageService
    def springSecurityService

    static Integer ITEMS_BY_PAGE = 20


    def inbox() {
        def currentUser = springSecurityService.currentUser
        def offset = 0
        def sort
        def order = 'des'


        if (params.sort == 'fromId') {
            sort = 'fromId'
        } else if (params.sort == 'subject') {
            sort = 'subject'
        } else {
            sort = 'dateCreated'
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

        def result = directMessageService.getReceivedMessagesBySubject(currentUser.id, offset, ITEMS_BY_PAGE, sort, order)
        render view:'inbox', model:[user:currentUser, messages:result.messages, totalNum:result.totalNum, unreadedNum:result.unreadedNum, max:ITEMS_BY_PAGE, sort:sort, order:order]
    }

    def sent() {
        def currentUser = springSecurityService.currentUser
        def sort
        def order = 'des'


        if (params.sort == 'toId') {
            sort = 'toId'
        } else if (params.sort == 'subject') {
            sort = 'subject'
        } else {
            sort = 'dateCreated'
        }

        if (params.order == 'asc') {
            order = 'asc'
        }

        def result = directMessageService.getSentMessagesBySubject(currentUser.id, 0, -1, sort, order)


        render view:'sent', model:[user:currentUser, messages:result.messages, totalNum:result.totalNum, sort:sort, order:order]
    }

    def view() {
        def currentUser = springSecurityService.currentUser
        def message = Message.get(params.messageId)
        //If the message exists and the user can view the message
        if ((message) &&
            ((message.fromId == currentUser.id) || (message.toId == currentUser.id))) {
                def messages = directMessageService.findAllMessagesOnSubject(message)
                def otherUser = message.fromId == currentUser.id?User.get(message.toId):User.get(message.fromId)
                render view:'thread', model:[user:currentUser, messages:messages, subject:message.subject, otherUser:otherUser]
        } else {
            redirect mapping: 'inbox'
        }
    }

    def newMessage() {
        def currentUser = springSecurityService.currentUser
        def toUser = User.get(params.toId)
        if (toUser && params.subject) {
            directMessageService.sendMessage(currentUser.id, toUser.id, params.text, params.subject)
            flash.message = message(code: 'thread.success')
        } else {
            flash.error = message(code: 'thread.error')
        }
        redirect mapping: 'inbox'
        return
    }

}
