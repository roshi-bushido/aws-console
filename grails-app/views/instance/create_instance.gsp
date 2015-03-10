<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
    <link rel="stylesheet" href="${resource(dir: 'plugins', file: 'sky-forms/version-2.0.1/css/custom-sky-forms.css')}">
</head>

<body>
<div class="container content">
    <div class="row">
        <div class="col col-md-10 col-lg-offset-1">
            <g:form controller="instance" action="createInstance" class="sky-form" novalidate="novalidate" method="post" useToken="true">
                <header>Instance Creation Form</header>
                <fieldset>
                    <div class="row">
                        <section class="col col-6">
                            <label class="select">
                                <g:select name="purpose" from="${purposes}" optionKey="id" optionValue="name" noSelection="['null':'Choose Purpose']" value="${instance.purpose}"/>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="input">
                                <input type="text" name="name" placeholder="Instance Name" value="">
                            </label>
                        </section>
                    </div>

                    <div class="row">
                        <section class="col col-6">
                            <label class="select">
                                <g:select name="amiId" from="${amis}" optionKey="id" optionValue="name" noSelection="['null':'Choose Instance']" value="${instance.amiId}"/>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="select">
                                <g:select name="type" from="${types}" optionKey="id" optionValue="name" noSelection="['null':'Choose Type']" value="${instance.type}"/>
                                <i></i>
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-6">
                            <label class="input">
                                <i class="icon-append fa fa-calendar"></i>
                                <input type="text" name="startingDate" id="start" placeholder="Start date">
                            </label>
                        </section>
                        <section class="col col-6">
                            <label class="input">
                                <i class="icon-append fa fa-calendar"></i>
                                <input type="text" name="endingDate" id="finish" placeholder="Expected finish date">
                            </label>
                        </section>
                    </div>
                    <section>
                        <label class="input">
                            <i class="icon-append fa fa-link"></i>
                            <input type="url" name="sfdcOpportunityLink" placeholder="Salesforce Opp Link">
                        </label>
                    </section>
                </fieldset>

                <fieldset>
                    <div class="row">
                        <section class="col col-4">
                            <label class="select">
                                <g:select name="amount" from="${0..100}" noSelection="['0':'Choose Amount']" value="${instance.amount}"/>
                                <i></i>
                            </label>
                        </section>
                        <section class="col col-4">
                            <label class="toggle state-success">
                                <input type="checkbox"  name="withDomain" class="checkbox-toggle" value="false" disabled="disabled">
                                <i class="rounded-4x"></i>Need a Domain
                            </label>
                        </section>
                        <section class="col col-4">
                            <label class="toggle state-success">
                                <input type="checkbox" name="withElasticIP" class="checkbox-toggle" value="false" disabled="disabled">
                                <i class="rounded-4x"></i>Need a Static IP?
                            </label>
                        </section>
                    </div>
                    <div class="row">
                        <section class="col col-8">
                            <label class="input">
                                <input type="url" name="domain" placeholder="Domain" disabled="disabled">
                                <span class="input-group-btn">
                                    <label class="input">.demos.mulesoft.com</label>
                                </span>
                            </label>
                        </section>
                    </div>
                </fieldset>
                <fieldset>
                    <section>
                        <label class="textarea">
                            <i class="icon-append fa fa-comment"></i>
                            <textarea rows="5" name="description" placeholder="Tell us about your project"></textarea>
                        </label>
                    </section>
                </fieldset>
                <footer>
                    <button type="submit" class="btn-u right" style="float: right">Create Instance</button>
                    <g:link controller="user" action="myInstances" class="btn-u right btn-u-red" style="float: right; margin-right: 20px;">Cancel</g:link>
                </footer>
            </g:form>
        </div>
    </div>
</div>

<g:javascript src="../plugins/sky-forms/version-2.0.1/js/jquery-ui.min.js"/>
<g:javascript src="./plugins/datepicker.js"/>
<g:javascript>
    $(document).ready(function() {
        App.init();
        Datepicker.initDatepicker();
    });
</g:javascript>
</body>
</html>