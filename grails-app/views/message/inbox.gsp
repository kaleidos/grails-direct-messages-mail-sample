<%@ page defaultCodec="HTML" %>
<%@page import="net.kaleidos.directmessagesmailsample.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Inbox</title>
        <meta name="layout" content="base">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    </head>
    <body>
        <h1>My Messages</h1>
        <div></div>
        <div>
            <span>Inbox</span>
            <span>|</span>
            <span>Sent Items</span>
        </div>
        <div>
            <table>
                <tr>
                    <th>FROM</th>
                    <th>SUBJECT</th>
                    <th>RECEIVED</th>
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
