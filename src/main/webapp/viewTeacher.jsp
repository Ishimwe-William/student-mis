<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>View Course Details</title>
    <link rel="stylesheet" href="static/css/semesterList.css">
</head>
<body style="display: flex;flex-direction: column;justify-content: center;align-items: center;">
<jsp:include page="header.jsp"/>
<header>
    <h1>View Teacher Details</h1>
</header>
<h3>Name: ${teacher.name}</h3>
<table class="table">
    <thead>
    <tr>
        <th>Email</th>
        <th>Gender</th>
        <th>Date of Birth</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${teacher.email}</td>
            <td>${teacher.gender}</td>
            <td>${teacher.dob}</td>
        </tr>
    </tbody>
    <thead>
    <tr>
        <th>Date of Birth</th>
        <th>Phone</th>
        <th>Type</th>
    </tr>
    </thead>
    <tbody>
        <tr>
            <td>${teacher.dob}</td>
            <td>${teacher.phone}</td>
            <td>${teacher.type}</td>
        </tr>
    </tbody>
</table><br/><br/>
<label><strong><u>Assigned Courses:</u></strong></label>
<ul>
    <c:forEach var="course" items="${courses}">
        <li><strong>Name: </strong>${course.name}<br/>
            <strong>Code: </strong>${course.code}:<br/>
            <strong>Credits: </strong>${course.credits}<br/><br/>
       </li>
    </c:forEach>
</ul>
<form method="get" action="teacherServlet">
    <button class="edit-button" type="submit">Back</button>
</form>
<br/>
<jsp:include page="footer.html"/>
</body>
</html>
