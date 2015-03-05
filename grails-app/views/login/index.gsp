<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container content">
    <div class="row">
        <div class="col-md-4 col-md-offset-4 col-sm-6 col-sm-offset-3">
            <g:form controller="login" action="authenticate" method="POST" class="reg-page" useToken="true">
                <div class="reg-header">
                    <h2>Login to your account</h2>
                </div>
                <div class="input-group margin-bottom-20">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <g:textField placeholder="Email Address" class="form-control" id="username" name="_username" value="${username}" />
                </div>
                <div class="input-group margin-bottom-20">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <g:passwordField type="password" placeholder="Password" class="form-control" id="password" name="_password" />
                </div>

                <g:if test="${java.lang.Boolean.TRUE.equals(badCredentials)}" >
                    <div class="errorMessage">Wrong Username or Password. Please enter you Mulesoft SSO credentials</div>
                </g:if>
                <div class="row">
                    <div class="col-md-12">
                        <button class="btn-u pull-right" type="submit">Login</button>
                    </div>
                </div>
                <h4>Forget your Password?</h4>
                <p>No worries, <a class="color-green" href="http://sso.mulesoft.com">click here</a> to reset your password.</p>
            </g:form>
        </div>
    </div>
</div>
</body>
</html>