<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <title>Assign Teachers To Course</title>
    <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Assign Teachers To Course Form</h1>
<h4>Assign Teacher(s) to <c:out value="${course.code} | ${course.name} " /></h4>

<form id="assignmentForm_<c:out value="${course.course_def_id}" />" action="/teacherAssignment" method="post">
    <label for="teacherSelection">Select Teacher(s):</label>
    <select multiple id="teacherSelection" name="teacherIds">
        <c:forEach var="teacher" items="${teachers}">
            <option value="${teacher.teacher_id}">${teacher.name} | ${teacher.email}</option>
        </c:forEach>
    </select>
    <br/>
    <input name="action" type="hidden" value="assignTeachersToCourse">
    <input type="hidden" name="courseId" value="<c:out value="${course.course_def_id}" />">
    <button type="submit" class="btn btn-primary">Assign</button>
</form><br/>
<footer>
    <jsp:include page="footer.html"/>
</footer>
</body>
</html>
