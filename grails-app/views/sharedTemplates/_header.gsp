<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="#">Loan Application</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
            <sec:ifLoggedIn>
                <h1 style="color:red">Hello <sec:username /></h1>
                <li class="nav-item">
                    <a class="nav-link" href="/loan/index">Loan</a>
                </li>
                <sec:ifAnyGranted roles="ROLE_ADMIN">
                    <li class="nav-item">
                        <a class="nav-link" href="/user/index">User</a>
                    </li>
                </sec:ifAnyGranted>
                <li style="float: right">
                    <g:link controller='logout'>Logout</g:link>
                </li>

            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <a href="/user/create">Sign Up</a>
            </sec:ifNotLoggedIn>

        </ul>

    </div>
</nav>
