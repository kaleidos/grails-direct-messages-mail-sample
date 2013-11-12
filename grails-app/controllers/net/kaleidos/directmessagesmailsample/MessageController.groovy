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
        def order = 'asc'


        if (params.sort == 'fromId') {
            sort = 'fromId'
        } else if (params.sort == 'subject') {
            sort = 'subject'
        } else {
            sort = 'dateCreated'
        }

        if (params.order == 'des') {
            order = 'des'
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
        def order = 'asc'


        if (params.sort == 'toId') {
            sort = 'toId'
        } else if (params.sort == 'subject') {
            sort = 'subject'
        } else {
            sort = 'dateCreated'
        }

        if (params.order == 'des') {
            order = 'des'
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
                render view:'thread', model:[user:currentUser, messages:messages, subject:message.subject]
        } else {
            redirect mapping: 'inbox'
        }
    }

}
