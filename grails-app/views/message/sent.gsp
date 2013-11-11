<%@ page defaultCodec="HTML" %>
<%@page import="net.kaleidos.directmessagesmailsample.User"%>
<!DOCTYPE html>
<html>
    <head>
        <title><g:message code="sent.title" /></title>
        <meta name="layout" content="base">
        <link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
    </head>
    <body>
        <h1><g:message code="sent.myMessages" /></h1>
        <div></div>
        <div>
            <span><g:link mapping="inbox"><g:message code="sent.inbox" /></g:link></span>
            <span>|</span>
            <span class="selected"><g:message code="sent.sentItems" /></span>
        </div>
        <div>
            <table class="messages">
                <tr>
                    <th class="to"><g:message code="sent.to" /></th>
                    <th class="subject"><g:message code="sent.subject" /></th>
                    <th class="date"><g:message code="sent.sent" /></th>
                </tr>
                <g:each in="${messages}" var="entry">
                    <tr>
                        <td>${User.get(entry.toId).username}</td>
                        <td>
                            <g:if test="${entry.reply}"><g:message code="sent.re" />&nbsp;</g:if>
                            ${entry.subject}
                        </td>
                        <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></td>
                    </tr>
                </g:each>

            </table>
        </div>
    </body>
</html>
