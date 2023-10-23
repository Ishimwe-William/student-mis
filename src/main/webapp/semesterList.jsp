<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Semester List</title>
    <link rel="stylesheet" href="static/css/semesterList.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Semester List</h1>
<a class="new-semester" href="/semesterServlet?action=create">Create New Semester</a></a><br/><br/>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="semester" items="${semesters}">
        <tr>
            <td>${semester.name}</td>
            <td>${semester.start_date}</td>
            <td>${semester.end_date}</td>
            <td>
                <form action="/semesterServlet" method="get">
                    <input type="hidden" name="semester_id" value="${semester.semester_id}" />
                    <button class="edit-button" type="submit" name="action" value="edit">Edit</button>
                    <button class="delete-button" type="submit" name="action" value="delete">Delete</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table><br/><br/>

<jsp:include page="footer.html"/>
</body>
</html>
