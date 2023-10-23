<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="static/css/header.css">
</head>
<body>
<header>
    <div id="logo">
        <img src="static/img/AUCA-logo-wide-webblue-2.png" alt="AUCA Logo">
    </div>
    <nav>
        <ul>
            <li><a href="home.jsp">Home</a></li>
            <li><a href="/semesterServlet">Semester</a></li>
            <%
                if (session != null && session.getAttribute("user") != null) {
            %>
            <li><a href="/teacherServlet">Teacher</a></li>
            <%
            } else {
            %>
            <li><a href="/courseDefinitionServlet">Course</a></li>
            <%
                }
            %>
            <li><a href="/academicUnitHome">Academic Unit</a></li>
        </ul>
    </nav>
</header>
</body>
</html>
