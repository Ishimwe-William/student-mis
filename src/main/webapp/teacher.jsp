<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Teacher Information</title>
  <link rel="stylesheet" href="static/css/index.css">
</head>
<body>
<jsp:include page="header.jsp"/>
<h1>Teacher Information Form</h1>
<form action="/teacherServlet" method="post">
  <label for="name">Name:</label>
  <input type="text" name="name" id="name" required><br><br>

  <label for="dob">Date of Birth:</label>
  <input type="date" name="dob" id="dob" required><br><br>

  <label for="gender">Gender:</label>
  <select name="gender" id="gender" required>
    <option value="">--Select Gender--</option>
    <option value="Male">Male</option>
    <option value="Female">Female</option>
  </select><br><br>

  <label for="degree">Degree:</label>
  <select name="degree" id="degree" required>
    <option value="">--Select Degree--</option>
    <option value="MASTER">MASTER</option>
    <option value="PHD">PHD</option>
    <option value="PROFESSOR">PROFESSOR</option>
  </select><br><br>

  <label for="email">Email:</label>
  <input type="email" name="email" id="email" required><br><br>

  <label for="phone">Phone:</label>
  <input type="text" name="phone" id="phone" required><br><br>

  <label>Select Type:</label><br>
  <input type="radio" name="teacherType" id="tutor" value="Tutor" required> Tutor
  <input type="radio" name="teacherType" id="tutor_ass" value="Assistant Tutor" required> Assistant Tutor<br><br>

  <input type="submit" value="Submit"><br><br>
  <input name="action" value="create_teacher" hidden>
</form>
<footer>
  <jsp:include page="footer.html"/>
</footer>
</body>
</html>
