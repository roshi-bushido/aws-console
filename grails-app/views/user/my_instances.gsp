<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="main"/>
</head>

<body>
<div class="container content">
    <div class="row">
        <div class="col-md-12">
            <!--Basic Table-->
            <div class="panel panel-dark-blue margin-bottom-40">
                <div class="panel-heading">
                    <h3 class="panel-title"><i class="fa fa-tasks"></i>My Instances</h3>
                </div>
                <div class="panel-body">
                    <p>Some default panel content here. Nulla vitae elit libero, a pharetra augue. Aenean lacinia bibendum nulla sed consectetur. Aenean eu leo quam. Pellentesque ornare sem lacinia quam venenatis vestibulum. Nullam id dolor id nibh ultricies vehicula ut id elit.</p>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>Name</th>
                        <th class="hidden-sm">Type</th>
                        <th>Purpose</th>
                        <th>Status</th>
                        <th>Has EIP?</th>
                        <th>Has Domain?</th>
                    </tr>
                    </thead>
                    <tbody>
                        <g:each in="${instances}" var="instance" >
                            <tr>
                                <td>${instance.id}</td>
                                <td>${instance.name}</td>
                                <td>${instance.type.name}</td>
                                <td>${instance.purpose.name}</td>
                                <td>${instance.state.name}</td>
                                <td>${instance.withElasticIP}</td>
                                <td>${instance.withDomain}</td>
                            </tr>
                        </g:each>
                    </tbody>
                </table>
                <div class="panel-body" >
                    <ul class="pagination col col-6">
                        <li><a href="#">«</a></li>
                        <li class="active"><a href="#">1</a></li>
                        <li><a href="#">2</a></li>
                        <li><a href="#">3</a></li>
                        <li><a href="#">4</a></li>
                        <li><a href="#">5</a></li>
                        <li><a href="#">»</a></li>
                    </ul>
                    <div class="col col-6" style="float: right;margin: 20px 0">
                        <g:link controller="instance" action="newInstance" class="btn-u right btn-u-green" style="float: right">Launch Instance</g:link>
                    </div>
                </div>
            </div>
            <!--End Basic Table-->
        </div>
    </div>
</div>
</body>
</html>