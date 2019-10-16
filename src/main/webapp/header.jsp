<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html>
<head>
    <title>hello</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <style>
        body {
            background: #679fca
        }
        .text-white-50 {
            color: #5795c5;
        }
        .bg-blue {
            background-color: #3b79a9;
        }
        .box-shadow {
            box-shadow: 0 .25rem .75rem rgba(0, 0, 0, .05);
        }
        .tab {
            padding-left: 50px;
        }
    </style>
</head>
<body>
<div class="d-flex align-items-center p-3 my-3 text-white-50 bg-blue rounded box-shadow">
    <div class="lh-100">
        <h6 class="mb-0 text-white lh-100">${fn:toUpperCase("user database")}</h6>
    </div>
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="usersAgeSort" methods="post">Sort users by age</a>
        </h6>
    </div>
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="users" >Sort users by id</a>
        </h6>
    </div>
    <div class="lh-100">
        <h6 class="mb-0 lh-100 tab">
            <a class="text-white" href="oldestUser">Oldest user with phone number</a>
        </h6>
    </div>
    <div class="lh-100 ml-auto">
        <h6 class="mb-0 lh-100">
            <a class="text-white" href="index.jsp">Add another file</a>
        </h6>
    </div>
</div>
</body>
</html>

