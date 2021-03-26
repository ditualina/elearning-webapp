<!DOCTYPE html>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="<c:url value='/resources/css/bootstrap.css' />" rel="stylesheet"></link>
    <link href="<c:url value='/resources/css/app.css' />" rel="stylesheet"></link>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <style>
            /* Remove the navbar's default margin-bottom and rounded borders */
        .navbar {
            margin-bottom: 0;
            border-radius: 0;
        }

            /* Set height of the grid so .sidenav can be 100% (adjust as needed) */
        .row.content {
            height: 450px
        }

            /* Set gray background color and 100% height */
        .sidenav {
            border-radius: 0;
            padding-top: 20px;
            background-color: #f1f1f1;
            height: 100%;
        }

            /* Set black background color, white text and some padding */
        footer {
            background-color: #555;
            color: white;
            padding: 15px;
        }

            /* On small screens, set height to 'auto' for sidenav and grid */
        @media screen and (max-width: 767px) {
            .sidenav {
                height: auto;
                padding: 15px;
            }

            .row.content {
                height: auto;
            }
        }
    </style>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#"></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li><sec:authorize access="hasRole('USER')">
                    <a href="<c:url value='/home-page' />">Home</a>
                </sec:authorize></li>
                <li><sec:authorize access="hasRole('USER')">
                    <a href="<c:url value='/courses' />">Courses</a>
                </sec:authorize></li>
                <li class="active"><a href="#">Projects</a></li>
                <li><sec:authorize access="hasRole('USER')">
                    <a href="<c:url value='/user-profile' />">Profile</a>
                </sec:authorize></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <li>
                    <a href=" <c:url value="/logout" />"><span class="glyphicon glyphicon-log-in"></span> Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid text-center">
    <div class="row content">
        <div class="col-sm-2 sidenav">
            <p><a href="http://www.en.ace.ucv.ro/invatamant/didactica/planuri.php">Education plan</a></p>

            <p><a href="http://www.en.ace.ucv.ro/invatamant/didactica/structura_anului_universitar.php">Academic year
                structure</a></p>

            <p><a href="http://www.en.ace.ucv.ro/invatamant/utile/orar.php">Schedule</a></p>

            <p><a href="http://www.en.ace.ucv.ro/invatamant/utile/examene.php">Exams</a></p>

            <p><a href="http://cis01.central.ucv.ro/evstud/">Student evidence</a></p>
        </div>
        <!-- here-->
        <div class="col-sm-8 text-left">
            <div>
                <h1>Upload your project/homework</h1>
            </div>
            <div class="dropdown">
                <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">${courseName}
                    <span class="caret"></span></button>
                <ul class="dropdown-menu">
                    <c:forEach items="${courses}" var="course">
                        <li>
                            <a href="<c:url value="/project" ><c:param name="course" value="${course.id}" /></c:url>">${course.name}</a>
                        </li>
                    </c:forEach>
                </ul>
            </div>
            <br> <br>
            <hr3></hr3>
            <hr>
            <div>
                <form method="POST" action="uploadOneFile?${_csrf.parameterName}=${_csrf.token}" enctype="multipart/form-data">
                    File to upload: <input type="file" name="file"><br />
                    <br /> <br />
                    <input type="submit" value="Upload"> Press here to upload the file!
                </form>
            </div>

        </div>
        <div class="col-sm-2 sidenav">
        </div>
    </div>

</div>
</div>

</body>
</html>
