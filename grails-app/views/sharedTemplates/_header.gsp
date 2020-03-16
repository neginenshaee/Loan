<nav class="navbar navbar-expand-lg navbar-dark bg-dark">

    <link rel="shortcut icon" href="${(resource(dir: 'images',file: 'neovisionico.gif'))}" type="image/x-icon"/>
    <div role="banner"><a href="http://neovision.com"><img src="${(resource(dir: 'images',file: 'neovision.png'))}" alt="Grails"/></a></div>

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
                <li>
                    <a class="nav-link" href="/user/password">Change Password</a>
                </li>
                <li>

                    <g:link controller='logout'>Logout</g:link>
                </li>

            </sec:ifLoggedIn>
            <sec:ifNotLoggedIn>
                <a href="/user/create">Sign Up</a>
            </sec:ifNotLoggedIn>

        </ul>

    </div>
</nav>
