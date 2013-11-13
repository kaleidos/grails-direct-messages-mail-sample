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
                ${otherUser.username}
            </h2>
        </div>
        <g:each in="${messages}" status="i" var="entry">
        <div class="thread_item">
            <div>
                <span class="half">
                    <div><g:message code='thread.to'/>: ${User.get(entry.toId).username}</div>
                    <div><g:message code='thread.from'/>: ${User.get(entry.fromId).username}</div>
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
        </g:each>
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
