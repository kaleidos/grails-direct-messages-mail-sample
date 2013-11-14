<%@ page defaultCodec="HTML" %>
<%@page import="net.kaleidos.directmessagesmailsample.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title><g:message code="userList.newMessage" /></title>
        <meta name="layout" content="base">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    </head>
    <body>

        <h1><g:link mapping="inbox"><g:message code="inbox.myMessages" /></g:link> &gt; <g:message code="userList.newMessage" /></h1>
        <div></div>
        <div>
            <h2>
                <g:message code="userList.select" />
            </h2>
        </div>
        <div>
            <table>
                <g:each in="${userList}" var="u">
                <tr>
                    <td>${u.name}</td>
                    <td><g:link mapping="newMessage" params="[toId:u.id]"><g:message code='userList.sendTo'/></g:link></td>
                </tr>
                </g:each>
            </table>
        </div>
    </body>
</html>
