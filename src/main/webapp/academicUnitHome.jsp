<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <script src="/webjars/jquery/3.6.4/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            // Populate the programmes dropdown when the page loads
            $.ajax({
                type: "GET",
                url: "academicUnitHome",
                data: {operation: "get_programs"}, // Request programs data
                dataType: "json",
                success: function (data) {
                    let programSelect = $("#programmeSelect");
                    programSelect.empty();
                    programSelect.append('<option value="">--Select Programme--</option>'); // Add a default option
                    $.each(data, function (key, value) {
                        programSelect.append($('<option></option>').attr("value", value.id).text(value.name));
                    });

                    // Bind change event to the programs dropdown
                    programSelect.change(function () {
                        let selectedProgramId = $(this).val();
                        if (selectedProgramId !== "") {
                            populateFaculties(selectedProgramId);
                        }
                    });
                }
            });

            // Function to populate the faculty dropdown based on the selected program
            function populateFaculties(selectedProgramId) {
                $.ajax({
                    type: "GET",
                    url: "academicUnitHome",
                    data: {
                        operation: "get_faculty",
                        selectedProgramId: selectedProgramId
                    },
                    dataType: "json",
                    success: function (data) {
                        let faculty_Select = $("#faculty_Select");
                        faculty_Select.empty();
                        faculty_Select.append('<option value="">--Select Faculty--</option>');
                        $.each(data, function (key, value) {
                            faculty_Select.append($('<option></option>').attr("value", value.id).text(value.name));
                        });
                    }
                });
            }

            $("#department").submit(function (event) {
                event.preventDefault(); // Prevent the form from submitting traditionally

                let dep_name = $("#dep_name").val();
                let dep_code = $("#dep_code").val();
                let selectedFacultyId = $("#faculty_Select").val();

                // Check if all required data is available
                if (dep_name && dep_code && selectedFacultyId) {
                    createDepartment(dep_name, dep_code, selectedFacultyId);
                }
            });

            // Function to create a department via AJAX
            function createDepartment(name, code, selectedFacultyId) {
                $.ajax({
                    type: "POST",
                    url: "/academicUnitHome", // Update the URL as needed
                    data: {
                        action: "create_department",
                        dep_name: name,
                        dep_code: code,
                        faculty_id: selectedFacultyId
                    },
                    dataType: "json"
                });
            }

            $("#faculty").submit(function (event) {
                event.preventDefault(); // Prevent the form from submitting traditionally

                let fac_name = $("#fac_name").val();
                let fac_code = $("#fac_code").val();
                let selectedProgrammeId = $("#programmeSelect").val();

                // Check if all required data is available
                if (fac_name && fac_code && selectedProgrammeId) {
                    createFaculty(fac_name, fac_code, selectedProgrammeId);
                }
            });

            // Function to create a department via AJAX
            function createFaculty(fac_name, fac_code, selectedProgrammeId) {
                $.ajax({
                    type: "POST",
                    url: "/academicUnitHome", // Update the URL as needed
                    data: {
                        action: "create_faculty",
                        fac_name: fac_name,
                        fac_code: fac_code,
                        programme_id: selectedProgrammeId
                    },
                    dataType: "json"
                });
            }
        });

        function showProgrammeForm() {
            let facultyForm = document.getElementById("faculty");
            let departmentForm = document.getElementById("department");
            let programmeForm = document.getElementById("programmeForm");
            let show_prog_drop = document.getElementById("show_prog_drop");

            show_prog_drop.style.display = "none";
            programmeForm.style.display = "block";
            facultyForm.style.display = "none";
            departmentForm.style.display = "none";
        }

        function showFacultyForm() {
            let facultyForm = document.getElementById("faculty");
            let departmentForm = document.getElementById("department");
            let programmeForm = document.getElementById("programmeForm");
            let show_prog_drop = document.getElementById("show_prog_drop");

            facultyForm.style.display = "block";
            show_prog_drop.style.display = "block";
            departmentForm.style.display = "none";
            programmeForm.style.display = "none";
        }
        function showDepartmentForm() {
            let facultyForm = document.getElementById("faculty");
            let departmentForm = document.getElementById("department");
            let programmeForm = document.getElementById("programmeForm");
            let show_prog_drop = document.getElementById("show_prog_drop");

            show_prog_drop.style.display = "block";
            facultyForm.style.display = "none";
            programmeForm.style.display = "none";
            departmentForm.style.display = "block";
        }
    </script>
    <title>Academic Unit Management</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<header>
    <a href="#" onclick="showProgrammeForm()">Create Programme</a>
    <a href="#" onclick="showFacultyForm()">Create Faculty</a>
    <a href="#" onclick="showDepartmentForm()">Create Department</a>
</header>
<form id="programmeForm" action="/academicUnitHome" method="post" style="display: none">
    <br/><label>Create Programme</label>
    <input type="hidden" name="action" value="create_programme">
    <label for="prog_name"></label><input id="prog_name" name="prog_name" type="text" required placeholder="Programme Name">
    <input id="prog_code" name="prog_code" type="text" required placeholder="Programme Code">
    <button type="submit">Create</button>
</form><br/>

<div id="show_prog_drop" style="display: none">
    <br/><label for="programmeSelect">Select Program:</label>
    <select id="programmeSelect" name="programme_select">
        <!-- Programs will be populated dynamically using JavaScript -->
    </select>
</div>

<form id="faculty" action="/academicUnitHome" method="post" style="display: none">
    <br/> <label>Create Faculty</label>
    <input type="hidden" name="action" value="create_faculty">
    <input id="fac_name" name="fac_name" type="text" required placeholder="Faculty Name">
    <input id="fac_code" name="fac_code" type="text" required placeholder="Faculty Code">
    <button type="submit">Create</button>
</form>

<form id="department" action="/academicUnitHome" style="display: none" method="post">
    <br/><label>Create Department</label>
    <input type="hidden" name="action" value="create_department">
    <select name="faculty_id" id="faculty_Select">
        <!-- Faculty options will be populated dynamically using JavaScript -->
    </select>
    <input id="dep_name" name="dep_name" type="text" placeholder="Department Name" required>
    <input id="dep_code" name="dep_code" type="text" placeholder="Department Code" required>
    <button type="submit" id="submitBtn">Create</button>
</form>
</body>
</html>
