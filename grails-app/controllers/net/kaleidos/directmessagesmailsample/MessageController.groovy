package net.kaleidos.directmessagesmailsample
import net.kaleidos.directmessages.DirectMessageService
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

}
