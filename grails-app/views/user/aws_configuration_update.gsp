<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container content">
    <div class="row">
        <div class="col-md-6 col-sm-6 col-sm-offset-3">
            <g:form controller="user" action="updateAwsConfiguration" method="POST" class="reg-page" useToken="true">
                <div class="reg-header">
                    <h3>Enter your AWS Credentials</h3>
                </div>
                <div class="input-group margin-bottom-20">
                    <span class="input-group-addon"><i class="fa fa-user"></i></span>
                    <g:textField placeholder="Client ID" class="form-control" name="clientId" value="${clientId}" required="true" />
                </div>

                <div class="input-group margin-bottom-20">
                    <span class="input-group-addon"><i class="fa fa-lock"></i></span>
                    <g:textField placeholder="Client Secret" class="form-control" name="clientSecret" value="${clientSecret}" required="true" />
                    <span class="input-group-btn">
                        <button class="btn btn-success" type="button" id="testConnectionButton">Validate!</button>
                    </span>
                </div>
                <div class="errorMessage" id="errorMessage" style="display: none;">Invalid Client ID and Client Secret</div>
                <div class="successMessage" id="successMessage" style="display: none;">Successfully validated credentials</div>
                <div class="col-md-13">
                    <button class="btn-u pull-right" type="submit">Update Configuration</button>
                </div>
            </g:form>

            <g:javascript>
                $("#testConnectionButton").click(function(event) {
                    var testConnectionUrl = '${createLink(controller: "user", action: "testConnection")}';
                    var clientId = $("#clientId").val();
                    var clientSecret = $("#clientSecret").val();

                    $.ajax({
                        url: testConnectionUrl,
                        data: {clientId: clientId, clientSecret: clientSecret},
                        type: 'GET',
                        contentType: 'application/json',
                        datatype: 'json'
                    }).done(function(response) {
                        var isValid = response.validCredentials;
                        if (isValid) {
                            $("#errorMessage").hide();
                            $("#successMessage").show();
                        } else {
                            $("#successMessage").hide();
                            $("#errorMessage").show();
                        }
                    }).fail(function( jqXHR, textStatus ) {
                        alert(textStatus);
                    });
                });
            </g:javascript>
        </div>
    </div>
</div>
</body>
</html>