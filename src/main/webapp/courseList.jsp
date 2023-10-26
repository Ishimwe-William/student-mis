<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Course List</title>
    <link rel="stylesheet" href="static/css/semesterList.css">
</head>
<body style="display: flex;flex-direction: column;justify-content: center;align-items: center;width:auto;height: 100vh;">
<jsp:include page="header.jsp"/>
<h1>Course List</h1>
<a class="new-semester" href="/courseDefinitionServlet?action=create">Create New Course</a></a><br/><br/>
<table class="table" style="width: auto">
    <thead>
    <tr>
        <th>Course Name</th>
        <th>Course Code</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="course" items="${courses}">
        <tr>
            <td>${course.name}</td>
            <td>${course.code}</td>
            <td>
                <form action="/courseDefinitionServlet" method="get">
                    <input type="hidden" name="course_id" value="${course.course_def_id}" />
                    <div>
                        <button class="edit-button" type="submit" name="action" value="edit">Edit</button>
                        <button class="edit-button" type="submit" name="action" value="view">View</button>
                        <button class="edit-button" type="submit" name="action" value="add_teachers">Assign Teacher(s)</button>
                        <button class="delete-button" type="submit" name="action" value="delete">Delete</button>
                    </div>
                </form>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table><br/><br/>

<jsp:include page="footer.html"/>
</body>
</html>
