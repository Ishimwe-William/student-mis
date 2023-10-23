<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Edit Semester</title>
    <link rel="stylesheet" href="static/css/editSemester.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Edit Applicant</h1>
<form action="studentApplication" method="post">
    <input type="hidden" name="action" value="update">
    <input type="hidden" name="email" value="${studentToEdit.email}">
    <label for="name">Names:</label>
    <input type="text" id="name" name="name" value="${studentToEdit.fullName}" required><br>

    <label for="dob">Date of Birth:</label>
    <input type="date" id="dob" name="dob" value="${studentToEdit.dob}" required><br>

    <label for="phone">Phone Number:</label>
    <input type="text" id="phone" name="phone" value="${studentToEdit.phone}" required><br/>

    <label for="parent">Parent:</label>
    <input type="text" id="parent" name="parent" value="${studentToEdit.parent}" required><br/>

    <label for="parPhone">Parent Phone:</label>
    <input type="text" id="parPhone" name="parPhone" value="${studentToEdit.parPhone}" required><br/>

    <label for="newEmail">Email:</label>
    <input type="email" id="newEmail" name="newEmail" value="${studentToEdit.email}" required><br/><br/>
    <button id="update-btn" type="submit" >Update</button><br/>
</form>
<a href="studentApplication">Back to Applicants List</a><br/><br/>
<jsp:include page="footer.html"/>
</body>
</html>
