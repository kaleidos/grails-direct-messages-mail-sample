<%@ page defaultCodec="HTML" %>
<%@page import="net.kaleidos.directmessagesmailsample.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title><g:message code="inbox.title" /></title>
        <meta name="layout" content="base">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    </head>
    <body>
        <h1><g:link mapping="inbox"><g:message code="inbox.myMessages" /></g:link> &gt; ${messages[0].subject}</h1>
        <div></div>
        <div>
            <h2>
                <g:message code="thread.conversation" />
                ${messages[0].toId == user.id?User.get(messages[0].fromId).username:User.get(messages[0].toId).username}
            </h2>
        </div>
        <g:each in="${messages}" var="entry">
        <div class="thread_item">
            <div><g:message code='thread.to'/>: ${User.get(entry.toId).username}</div>
            <div><g:message code='thread.from'/>: ${User.get(entry.fromId).username}</div>
            <div><g:message code='thread.date'/>: <g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></div>
            <div class="text">${entry.text}</div>
        </div>
        </g:each>


    </body>
</html>
