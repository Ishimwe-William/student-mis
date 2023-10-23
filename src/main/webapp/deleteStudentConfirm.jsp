<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Confirm Delete Applicant</title>
    <link rel="stylesheet" href="static/css/editSemester.css"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Confirm Delete Applicant</h1>
<p>Are you sure you want to delete the following applicant?</p>
<p><strong>Name:</strong> ${studentToDelete.fullName}</p>
<p><strong>email:</strong> ${studentToDelete.email}</p>
<p><strong>Major:</strong> ${studentToDelete.major}</p>

<form action="studentApplication" method="post">
    <input type="hidden" name="action" value="confirmDelete">
    <input type="hidden" name="email" value="${studentToDelete.email}">
    <input type="submit" value="Confirm Delete">
</form>
<a href="studentApplication">Cancel</a><br/><br/>
</body>
</html>
