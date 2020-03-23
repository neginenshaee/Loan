<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Request Loan</title>
    <meta name="layout" content="main"/>
    <asset:stylesheet src="bootstrap.css" rel="stylesheet"/>
</head>

<body>
<g:form controller="loanRequest" action="save" method="post">
    <div class="container">
        <div class="custom-wrapper">
            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <input type="number" class="form-control custom-input" name="amount" placeholder="Amount">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <input type="date" class="form-control custom-input" name="deadline" placeholder="Deadline">
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <div class="form-group">
                        <textarea  type="text" class="form-control custom-input" name="description" placeholder="Description" rows="3"></textarea>
                    </div>
                </div>
            </div>

            <div class="row">
                <div class="col">
                    <button type="submit" class="btn btn-primary">Request</button>
                </div>
            </div>
        </div>








    </div>
</g:form>
</body>
</html>