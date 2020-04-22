<!doctype html>
<html>
    <head>
        <title>Not Found</title>
        <meta name="layout" content="main">
        <g:if env="development"><asset:stylesheet src="errors.css"/></g:if>
    </head>
    <body>
        <ul class="errors">

            <g:if test="${flash.message}">
                <div class="message_error">
                    <li>${flash.message}</li>
                </div>
            </g:if>
%{--            <g:else>--}%
%{--                <li>Error: Page Not Found (404)</li>--}%
%{--            </g:else>--}%
            <li>Path: ${request.forwardURI}</li>
        </ul>
    </body>
</html>
