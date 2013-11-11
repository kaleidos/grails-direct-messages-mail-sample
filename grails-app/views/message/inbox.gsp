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
        <h1><g:message code="inbox.myMessages" /></h1>
        <div></div>
        <div>
            <span><g:message code="inbox.inbox" /></span>
            <span>|</span>
            <span><g:message code="inbox.sentItems" /></span>
        </div>
        <div>
            <table>
                <tr>
                    <th><g:message code="inbox.from" /></th>
                    <th><g:message code="inbox.subject" /></th>
                    <th><g:message code="inbox.received" /></th>
                </tr>
                <g:each in="${messages}" var="entry">
                    <tr>

                        <g:if test="${entry.fromId == user.id}">
                            <g:set var="userMessage" value="${User.get(entry.toId)}" />
                        </g:if>
                        <g:else>
                            <g:set var="userMessage" value="${User.get(entry.fromId)}" />
                        </g:else>
                        <td>${userMessage.username}</td>
                        <td>${entry.subject}</td>
                        <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></td>
                    </tr>
                </g:each>

            </table>
        </div>
    </body>
</html>
