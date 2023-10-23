<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Semester</title>
    <link rel="stylesheet" href="static/css/semesterList.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Create Semester</h1>
<form action="/semesterServlet" method="post">
    <input type="hidden" name="action" value="create">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>
    <label for="start_date">Start Date:</label>
    <input type="date" id="start_date" name="start_date" required><br>
    <label for="end_date">End Date:</label>
    <input type="date" id="end_date" name="end_date" required><br>
    <input type="submit" value="Create Semester">
</form>
<a href="/semesterServlet">Back to Semester List</a><br/><br/>
<jsp:include page="footer.html"/>
</body>
</html>
