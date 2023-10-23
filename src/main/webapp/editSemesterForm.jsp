<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Edit Semester</title>
  <link rel="stylesheet" href="static/css/editSemester.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Edit Semester</h1>
<form action="/semesterServlet" method="post">
  <input type="hidden" name="action" value="update">
  <input type="hidden" name="semester_id" value="${semesterToEdit.semester_id}">
  <label for="name">Name:</label>
  <input type="text" id="name" name="name" value="${semesterToEdit.name}" required><br>
  <label for="start_date">Start Date:</label>
  <input type="date" id="start_date" name="start_date" value="${semesterToEdit.start_date}" required><br>
  <label for="end_date">End Date:</label>
  <input type="date" id="end_date" name="end_date" value="${semesterToEdit.end_date}" required><br>
  <button id="update-btn" type="submit" >Update</button>
</form>
<a href="/semesterServlet">Back to Semester List</a><br/><br/>
<jsp:include page="footer.html"/>
</body>
</html>
