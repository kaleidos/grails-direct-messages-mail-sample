<%@ page defaultCodec="HTML" %>
<%@page import="net.kaleidos.directmessagesmailsample.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title>${messages[0].subject}</title>
        <meta name="layout" content="base">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    </head>
    <body>

        <h1><g:link mapping="inbox"><g:message code="inbox.myMessages" /></g:link> &gt; ${messages[0].subject}</h1>
        <div></div>
        <div>
            <h2>
                <g:message code="thread.conversation" />
                ${otherUser.name}
            </h2>
        </div>
        <g:set var="numHidden" value="${0}" />
        <g:each in="${messages}" status="i" var="entry">
            <g:if test="${entry.isDeletedForUser(user.id)}">
                <g:set var="numHidden" value="${numHidden+1}" />
                <div class="thread_container hidden">
            </g:if>
            <g:else>
                <div class="thread_container">
            </g:else>
                <div class="thread_item">
                    <div>
                        <span class="half">
                            <div><g:message code='thread.to'/>: ${User.get(entry.toId).name}</div>
                            <div><g:message code='thread.from'/>: ${User.get(entry.fromId).name}</div>
                            <div><g:message code='thread.date'/>: <g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></div>
                        </span>
                        <g:if test="${i == messages.size()-1}">
                            <span class="half right">
                                <div><g:link mapping="reportUser" params="[userId:otherUser.id]"><g:message code="thread.report" /></g:link></div>
                                <div><g:link mapping="blockUser" params="[userId:otherUser.id]"><g:message code="thread.block" /></g:link></div>
                            </span>
                        </g:if>
                    </div>
                    <div class="text">
                        <g:lines string="${entry.text}" />
                    </div>
                </div>
            </div>
        </g:each>
        <g:if test="${numHidden > 0}">
            <div id="show_deleted_message" class="message">
                <g:message code='thread.deletedMessages' args="[numHidden]"/>&nbsp;
                <a href="#" onClick="return showDeletedMessages()"><g:message code='thread.deletedMessages.view'/></a>
            </div>
        </g:if>
        <div class="new_message">
            <g:message code='thread.reply'/>
            <form method="post" action="<g:createLink mapping='newMessage' />" onsubmit="return checkNewMessage()">
                <input id="subject" type="hidden" name="subject" value="${messages[0].subject}" />
                <input type="hidden" name="toId" value="${otherUser.id}" />
                <textarea id="text" class="new_message_text" name="text" maxlength="5000"></textarea>
                <div class="submit_button">
                    <input type="submit" value="<g:message code='thread.send'/>" />
                </div>
            </form>
        </div>


    </body>
</html>
