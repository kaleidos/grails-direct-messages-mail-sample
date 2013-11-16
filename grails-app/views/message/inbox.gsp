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
        <div class="right">
            <g:link mapping="userList"><g:message code='userList.newMessage'/></g:link>
        </div>
        <div>
            <span class="selected"><g:message code="inbox.inbox" /></span>
            <span>|</span>
            <span><g:link mapping="sent"><g:message code="inbox.sentItems" /></g:link></span>
        </div>
        <div>
            <g:form mapping="deleteMessages" method="post">
                <table class="messages">
                    <tr>
                        <th class="delete">&nbsp;</th>
                        <th class="icon">&nbsp;</th>
                        <th class="from">
                            <g:render template="sortableHeader" model="[sort:sort, order:order, label:'inbox.from', field:'fromName', mapping:'inbox']"/>
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
                            <td><input type="checkbox" name="deleteId" value="${entry.id}" /></td>
                            <td>
                                <g:if test="${! entry.lastOnThread}">
                                    <img src="${resource(dir: 'images', file: 'arrowLeft.png')}" />
                                </g:if>
                                <g:else>
                                    <img src="${resource(dir: 'images', file: 'letter.png')}" />
                                </g:else>

                            </td>
                            <td>
                                ${User.get(entry.fromId).name}
                                (${entry.numberOfMessagesOnThread})
                            </td>
                            <td>
                                <g:link mapping="view" params="[messageId:entry.id]">
                                    <g:if test="${entry.reply}"><g:message code="inbox.re" />:&nbsp;</g:if>
                                    ${entry.subject}
                                </g:link>
                            </td>
                            <td><g:formatDate format="yyyy-MM-dd HH:mm" date="${entry.dateCreated}"/></td>
                        </tr>
                    </g:each>

                </table>
                <div>
                    &nbsp;<input type="submit" value="<g:message code="inbox.delete" />" />
                </div>
            </g:form>
        </div>
        <div class="paginateButtons">
            <g:paginate mapping="inbox" total="${totalNum}" max="${max}"/>
        </div>
    </body>
</html>
