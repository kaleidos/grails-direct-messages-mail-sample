package net.kaleidos.directmessagesmailsample
import net.kaleidos.directmessages.ThreadMessageService
import net.kaleidos.directmessages.Message


class MailMessagingService {
    def threadMessageService
    /**
     * Send a message from an user to another
     * Encapsulates the method sendThreadMessage from threadMessageService
     * @param from User that send the message
     * @param to User that receives the message
     * @param text The text of the message
     * @param subject The subject of the message
     * @return a Message
     */
    Message sendMessage(User from, User to, String text, String subject) {
        return threadMessageService.sendThreadMessage(from.id, to.id, from.name, to.name, text, subject)
    }
}
