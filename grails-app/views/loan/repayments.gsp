<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main" />
    <g:set var="entityName" value="${message(code: 'repayment.label', default: 'Repayment')}" />
</head>

<body>
<g:each in="${request}" var="award">
    <g:each in="${award}" var="var">
        ${var}
    </g:each>
    <br>
</g:each>
</body>
</html>