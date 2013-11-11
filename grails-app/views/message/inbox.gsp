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
            <span class="selected"><g:message code="inbox.inbox" /></span>
            <span>|</span>
            <span><g:link mapping="sent"><g:message code="inbox.sentItems" /></g:link></span>
        </div>
        <div>
            <table class="messages">
                <tr>
                    <th class="from"><g:message code="inbox.from" /></th>
                    <th class="subject"><g:message code="inbox.subject" /></th>
                    <th class="date"><g:message code="inbox.received" /></th>
                </tr>
                <g:each in="${messages}" var="entry">
                    <tr>
                        <td>${User.get(entry.fromId).username}</td>
                        <td>
                            <g:if test="${entry.reply}"><g:message code="inbox.re" />&nbsp;</g:if>
                            ${entry.subject}
                        </td>
                        <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></td>
                    </tr>
                </g:each>

            </table>
        </div>
        <div class="paginateButtons">
            <g:paginate mapping="inbox" total="${totalNum}" max="${max}"/>
        </div>
    </body>
</html>
