<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Request Loan</title>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>

<header>
    <g:render template="/sharedTemplates/header"/>
</header>

<g:form controller="loanRequest" action="save" method="post">
    <div class="container">

        <div class="form-group">
            <input type="number" class="form-control" name="amount" placeholder="Amount">
        </div>


        <div class="form-group">
            <input type="date" class="form-control" name="deadline" placeholder="Deadline">
        </div>

        <div class="form-group">
            <textarea  type="text" class="form-control" name="desc" placeholder="Description" rows="3"></textarea>
        </div>


        <button type="submit" class="btn btn-primary">Request</button>
    </div>
</g:form>
<footer>
    <g:render template="/sharedTemplates/footer"/>
</footer>
</body>
</html>