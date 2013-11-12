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
        <h1><g:message code="inbox.myMessages" /> (${unreadedNum})</h1>
        <div></div>
        <div>
            <span class="selected"><g:message code="inbox.inbox" /></span>
            <span>|</span>
            <span><g:link mapping="sent"><g:message code="inbox.sentItems" /></g:link></span>
        </div>
        <div>
            <table class="messages">
                <tr>
                    <th class="delete">&nbsp;</th>
                    <th class="icon">&nbsp;</th>
                    <th class="from">
                        <g:render template="sortableHeader" model="[sort:sort, order:order, label:'inbox.from', field:'fromId', mapping:'inbox']"/>
                    </th>
                    <th class="subject">
                        <g:render template="sortableHeader" model="[sort:sort, order:order, label:'inbox.subject', field:'subject', mapping:'inbox']"/>
                    </th>
                    <th class="date">
                        <g:render template="sortableHeader" model="[sort:sort, order:order, label:'inbox.received', field:'dateCreated', mapping:'inbox']"/>
                    </th>
                </tr>
                <g:each in="${messages}" var="entry">
                    <tr <g:if test="${!entry.readed}">class="unreaded"</g:if>>
                        <td></td>
                        <td>
                            <g:if test="${entry.lastOnSubject}">
                                <img src="${resource(dir: 'images', file: 'arrowLeft.png')}" />
                            </g:if>
                            <g:else>
                                <img src="${resource(dir: 'images', file: 'letter.png')}" />
                            </g:else>

                        </td>
                        <td>${User.get(entry.fromId).username}</td>
                        <td>
                            <g:if test="${entry.reply}"><g:message code="inbox.re" />:&nbsp;</g:if>
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
