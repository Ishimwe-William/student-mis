<%@ page import="com.bunsen.studentmis.model.ERegistrationStatus" %>
<%@ page import="com.bunsen.studentmis.controller.StudentApplicationServlet" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Applied Student List</title>
    <link rel="stylesheet" href="static/css/semesterList.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Applied Student List</h1>
<div style="display: flex; justify-content: space-between;">
    <a class="new-semester" href="/studentApplication?action=create">Create New Applicant</a>
    <a class="new-semester" href="/studentApplication?action=view_rejected">View Rejected Applicant</a>
    <a class="new-semester" href="/studentApplication?action=view_admitted">View Admitted Student</a>
</div><br/>
<table class="table">
    <thead>
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Date of Birth</th>
        <th>Phone Number</th>
        <th>Parent Name</th>
        <th>Parent Phone Number</th>
        <th>Chosen Major</th>
        <th>Status</th>
        <th>Actions</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="student" items="${applied_students}">
        <c:choose>
        <c:when test="${student.registrationStatus == ERegistrationStatus.PENDING}">
        <!-- Code to display information for students with PENDING status -->
        <tr>
            <td>${student.fullName}</td>
            <td>${student.email}</td>
            <td>${student.dob}</td>
            <td>${student.phone}</td>
            <td>${student.parent}</td>
            <td>${student.parPhone}</td>
            <td>${student.department.name}</td>
            <td>${student.registrationStatus}</td>
            <td>
                <form action="studentApplication" method="get">
                    <input type="hidden" name="student_email" value="${student.email}" />
                    <input type="hidden" name="department_id" value="${student.department.id}" />
                    <button class="edit-button" type="submit" name="action" value="approve">Approve</button>
                    <button class="edit-button" type="submit" name="action" value="edit">Edit</button>
                    <button class="delete-button" type="submit" name="action" value="delete">Delete</button>
                    <button class="delete-button" type="submit" name="action" value="reject">Reject</button>
                </form>
            </td>
        </tr>
        </c:when>
            <c:otherwise>
                <!-- Code to handle students with other statuses -->
            </c:otherwise>
        </c:choose>
    </c:forEach>
    </tbody>
</table><br/><br/>

<jsp:include page="footer.html"/>
</body>
</html>
