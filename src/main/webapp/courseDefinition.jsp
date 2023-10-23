<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Course Definition</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Course Definition Form</h1>
<form action="/courseDefinitionServlet" method="post">
    <label for="name">Course Name:</label>
    <input type="text" name="name" id="name" required><br><br>

    <label for="code">Course Code:</label>
    <input type="text" name="code" id="code" required><br><br>

    <label for="description">Course Description:</label>
    <textarea type="text" name="description" id="description" required></textarea><br><br>

    <input type="submit" value="Submit"><br><br>
</form>
<jsp:include page="footer.html"/>
</body>
</html>
