<%@ page defaultCodec="HTML" %>
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
                <g:message code="userList.newMessage" />
            </h2>
        </div>

        <div class="new_message">
            <div>
                <g:message code='newMessage.to'/>: ${otherUser.username}
            </div>
            <g:form method="POST" mapping="newMessage">
                <input type="hidden" name="toId" value="${otherUser.id}" />
                <div>
                    <g:message code='newMessage.subject'/>:
                </div>
                <div>
                    <input class="subject" type="text" name="subject"/>
                </div>
                <br />
                <div>
                    <textarea class="new_message_text" name="text"></textarea>
                </div>
                <div class="submit_button">
                    <input type="submit" value="<g:message code='thread.send'/>" />
                </div>
            </g:form>
        </div>


    </body>
</html>
