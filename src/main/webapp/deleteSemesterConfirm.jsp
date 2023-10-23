<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Delete Semester</title>
    <link rel="stylesheet" href="static/css/editSemester.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Confirm Delete Semester</h1>
<p>Are you sure you want to delete the following semester?</p>
<p><strong>Name:</strong> ${semesterToDelete.name}</p>
<p><strong>Start Date:</strong> ${semesterToDelete.start_date}</p>
<p><strong>End Date:</strong> ${semesterToDelete.end_date}</p>

<form action="/semesterServlet" method="post">
    <input type="hidden" name="action" value="confirmDelete">
    <input type="hidden" name="semester_id" value="${semesterToDelete.semester_id}">
    <input type="submit" value="Confirm Delete">
</form>
<a href="/semesterServlet">Cancel</a><br/><br/>
</body>
</html>
