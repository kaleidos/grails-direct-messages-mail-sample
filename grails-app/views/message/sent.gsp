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
                    <th class="icon">&nbsp;</th>
                    <th class="to">
                        <g:render template="sortableHeader" model="[sort:sort, order:order, label:'sent.to', field:'toId', mapping:'sent']"/>
                    </th>
                    <th class="subject">
                        <g:render template="sortableHeader" model="[sort:sort, order:order, label:'sent.subject', field:'subject', mapping:'sent']"/>
                    </th>
                    <th class="date">
                        <g:render template="sortableHeader" model="[sort:sort, order:order, label:'sent.sent', field:'dateCreated', mapping:'sent']"/>
                    </th>
                </tr>
                <g:each in="${messages}" var="entry">
                    <tr>
                        <td>
                            <g:if test="${entry.lastOnSubject}">
                                <img src="${resource(dir: 'images', file: 'arrowLeft.png')}" />
                            </g:if>
                        </td>
                        <td>
                            ${User.get(entry.toId).username}
                            (${entry.numberOfMessagesOnSubject})
                        </td>
                        <td>
                            <g:link mapping="view" params="[messageId:entry.id]">
                                <g:if test="${entry.reply}"><g:message code="sent.re" />:&nbsp;</g:if>
                                ${entry.subject}
                            </g:link>
                        </td>
                        <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></td>
                    </tr>
                </g:each>

            </table>
        </div>
    </body>
</html>
