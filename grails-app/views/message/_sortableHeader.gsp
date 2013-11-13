<%@ page defaultCodec="HTML" %>
    <g:if test="${sort==field}">
        <g:if test="${order=='asc'}">
            <g:link mapping="${mapping}" params="[sort:field, order:'desc']"><g:message code="${label}" />
            <img src="${resource(dir: 'images', file: 'triangleDown.png')}" />
            </g:link>
        </g:if>
        <g:else>
            <g:link mapping="${mapping}" params="[sort:field, order:'asc']"><g:message code="${label}" />
            <img src="${resource(dir: 'images', file: 'triangleUp.png')}" />
            </g:link>
        </g:else>
    </g:if>
    <g:else>
        <g:link mapping="${mapping}" params="[sort:field, order:'asc']"><g:message code="${label}" /></g:link>
    </g:else>
