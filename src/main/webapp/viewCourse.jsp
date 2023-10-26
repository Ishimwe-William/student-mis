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
    <h1>View Course Details</h1>
</header>
<h3>${course.name}</h3>
<p><strong>Course Code:</strong> ${course.code}</p>
<div>
    <p><strong>Number of Credits:</strong>${course.credits}</p>
    <p><strong>Description:</strong>${course.description}</p>
</div>

<label><strong><u> Teachers:</u></strong></label>
<ul>
    <c:forEach var="teacher" items="${teachers}">
        <li><strong>Name: </strong>${teacher.name}<br/>
            <strong> Type: </strong>${teacher.type}:<br/>
            <strong>Degree: </strong>${teacher.degree}<br/><br/>
       </li>
    </c:forEach>
</ul>
<form method="get" action="courseDefinitionServlet">
    <button class="edit-button" type="submit">Back</button>
</form>
<br/>
<jsp:include page="footer.html"/>
</body>
</html>
