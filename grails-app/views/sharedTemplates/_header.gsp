<link href="https://fonts.googleapis.com/css?family=Raleway&display=swap" rel="stylesheet">
<link href="https://fonts.googleapis.com/css?family=Open+Sans&display=swap" rel="stylesheet">

    <nav class="navbar  navbar-expand-lg black-nav-top">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand smooth-scroll" href="#home"><img src="${(resource(dir: 'images',file: 'neovision.png'))}" width="70px" height="50px" alt="logo"/></a>
            </div>
            <div class="site-nav-wrapper">
                <link rel="shortcut icon" href="${(resource(dir: 'images',file: 'neovisionico.gif'))}" type="image/x-icon"/>
                <div class="container">
                    <div class="navbar-collapse">
                        <ul class="nav navbar-nav pull-right">

                            <sec:ifLoggedIn>
                                <li class="nav-item">
                                    <a class="nav-link" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a>
                                </li>
                                <li class="nav-item"><a class="nav-link" href="/loanRequest/index">Loan Request</a></li>

                                <li class="smooth-scroll nav-item"><a class="nav-link" href="/loanRequest/calculator">Calculator</a></li>
                                <sec:ifAnyGranted roles="ROLE_ADMIN">
                                    <li class="nav-item"><a class="nav-link" href="/user/index">User</a></li>
%{--                                    <li class="nav-item"><a class="nav-link" href="/repayment/index">Repayment</a></li>--}%
                                    <li class="nav-item"><a class="nav-link" href="/loan/index">Loan</a></li>
                                </sec:ifAnyGranted>
                                <li class="nav-item"><a class="nav-link" href="/user/password">Change Password</a></li>
                                <li class="nav-item"><a class="nav-link" href="/user/edit">Edit info</a></li>
                                <li class="nav-item">
                                    <a><sec:username /> <i class="fa fa-angle-down fa-lg"></i></a>

                                    <ul class="nav navbar-nav pull-right dropdown">
                                        <li class="nav-item"><g:link class="nav-link" controller='logout'>Logout</g:link></li>
                                    </ul>
                                </li>

                            </sec:ifLoggedIn>

                            <sec:ifNotLoggedIn>
                                <li><a class="smooth-scroll" href="#home">Home</a></li>
                                <li><a class="smooth-scroll" href="#about">About</a></li>
                                <li><a class="smooth-scroll" href="#team">Team</a></li>
                                <li><a class="smooth-scroll" href="#services">Services</a></li>
                                <li><a class="smooth-scroll" href="#portfolio">Work</a></li>
                                <li><a class="smooth-scroll" href="#blog">Blog</a></li>
                                <li><a class="smooth-scroll" href="#contact">Contact</a></li>
                                <li class="nav-item"><a class="nav-link" href="/login/auth">Login</a></li>
                                <li class="nav-item"><a class="nav-link" href="/user/create">Sign Up</a></li>
                            </sec:ifNotLoggedIn>

                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </nav>
