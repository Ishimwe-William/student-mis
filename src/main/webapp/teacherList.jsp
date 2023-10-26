<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Teachers List</title>
    <link rel="stylesheet" href="static/css/semesterList.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Teachers List</h1>
<a class="new-semester" href="/teacherServlet?action=create">Create New Teacher</a></a><br/><br/>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Gender</th>
        <th>Date of Birth</th>
        <th>Phone</th>
        <th>Type</th>
        <th>Created At</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="teacher" items="${teachers}">
        <tr>
            <td>${teacher.name}</td>
            <td>${teacher.email}</td>
            <td>${teacher.gender}</td>
            <td>${teacher.dob}</td>
            <td>${teacher.phone}</td>
            <td>${teacher.type}</td>
            <td>${teacher.created_at}</td>
            <td>
                <form action="/teacherServlet" method="get">
                    <input type="hidden" name="teacher_id" value="${teacher.teacher_id}" />
                    <button class="edit-button" type="submit" name="action" value="edit">Edit</button>
                    <button class="delete-button" type="submit" name="action" value="delete">Delete</button>
                    <button class="edit-button" type="submit" name="action" value="view">View</button>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table><br/><br/>

<jsp:include page="footer.html"/>
</body>
</html>
