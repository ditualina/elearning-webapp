<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Login page</title>
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" type="text/css"
          href="//cdnjs.cloudflare.com/ajax/libs/font-awesome/4.2.0/css/font-awesome.css"/>
    <link href="resources/tooplate_style.css" rel="stylesheet"
          type="text/css"/>
    <script src="resources/js/jquery.easing.1.3.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="resources/js/main_page.js" type="text/javascript"
            charset="utf-8"></script>
    <script src="resources/js/jquery-1.2.6.js" type="text/javascript"></script>

</head>

<body>
<div class="container-fluid text-center">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <div class="panel panel-login">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="<c:url value='/login' />" id="login-form-link">Login</a>
                        </div>
                        <div class="col-xs-6">
                            <a href="#" id="register-form-link" class="active">Register</a>
                        </div>
                    </div>
                    <hr>
                </div>
                <div class="panel-body">
                    <form:form method="POST" modelAttribute="user" class="form-horizontal">
                        <form:input type="hidden" path="id" id="id"/>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="firstName">First Name</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="firstName" id="firstName" class="form-control input-sm"/>
                                    <div class="has-error">
                                        <form:errors path="firstName" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="lastName">Last Name</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="lastName" id="lastName" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="lastName" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="ssoId">User Name</label>
                                <div class="col-md-7">
                                    <c:choose>
                                        <c:when test="${edit}">
                                            <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
                                        </c:when>
                                        <c:otherwise>
                                            <form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
                                            <div class="has-error">
                                                <form:errors path="ssoId" class="help-inline"/>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="password">Password</label>
                                <div class="col-md-7">
                                    <form:input type="password" path="password" id="password" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="password" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable" for="email">Email</label>
                                <div class="col-md-7">
                                    <form:input type="text" path="email" id="email" class="form-control input-sm" />
                                    <div class="has-error">
                                        <form:errors path="email" class="help-inline"/>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="form-group col-md-12">
                                <label class="col-md-3 control-lable">Scholar Year</label>
                                <div class="col-md-7">
                                    <input type="text" name="scholarYear" class="form-control input-sm" />
                                </div>
                            </div>
                        </div>

                        <div class="row">
                            <div class="col-sm-6 col-sm-offset-3">
                                   <input type="submit" value="Register" class="form-control btn btn-register"/>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>

