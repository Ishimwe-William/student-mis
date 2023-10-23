<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Admission Form</title>
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link rel="stylesheet" href="static/css/index.css">
    <script src="static/js/index.js"></script>
    <script src="static/js/logoutOnExit.js"></script>
    <script src="/webjars/jquery/3.6.4/jquery.min.js"></script>
    <script>
        $(document).ready(function () {
            // Update the existing code to populate the programs dropdown
            $.ajax({
                type: "GET",
                url: "academicUnitHome",
                data: {operation: "get_programs"},
                dataType: "json",
                success: function (data) {
                    let programSelect = $("#programme_select"); // Make sure this ID matches your HTML
                    programSelect.empty();
                    programSelect.append('<option value="">--Select Programme--</option>');
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


            // Update the existing function to populate faculties
            function populateFaculties(selectedProgramId) {
                $.ajax({
                    type: "GET",
                    url: "academicUnitHome",
                    data: {
                        operation: "get_faculty",
                        selectedProgramId: selectedProgramId,
                    },
                    dataType: "json",
                    success: function (data) {
                        let facultySelect = $("#faculty_Select"); // Make sure this ID matches your HTML
                        facultySelect.empty();
                        facultySelect.append('<option value="">--Select Faculty--</option>');
                        $.each(data, function (key, value) {
                            facultySelect.append($('<option></option>').attr("value", value.id).text(value.name));
                        });
                        // Bind change event to the faculty dropdown
                        facultySelect.change(function () {
                            let selectedFacultyId = $(this).val();
                            if (selectedFacultyId !== "") {
                                populateDepartments(selectedFacultyId); // Call the new function
                            }
                        });
                    }
                });
            }

            // Function to populate the department dropdown based on the selected faculty
            function populateDepartments(selectedFacultyId) {
                $.ajax({
                    type: "GET",
                    url: "academicUnitHome",
                    data: {
                        operation: "get_departments",
                        selectedFacultyId: selectedFacultyId,
                    },
                    dataType: "json",
                    success: function (data) {
                        let departmentSelect = $("#department_select"); // Make sure this ID matches your HTML
                        departmentSelect.empty();
                        departmentSelect.append('<option value="">--Select Department--</option>');
                        $.each(data, function (key, value) {
                            departmentSelect.append($('<option></option>').attr("value", value.id).text(value.name));
                        });

                        // Populate the hidden department_id input field
                        const selectedDepartmentId = departmentSelect.val();
                        $("#selected_department_id").val(selectedDepartmentId);
                    }
                });
            }

        });
        $("#admissionForm").submit(function (event) {
            event.preventDefault(); // Prevent the default form submission

            // Ensure that department_id is populated
            const selectedDepartmentId = $("#department_select").val();
            $("#selected_department_id").val(selectedDepartmentId);

            // Now, you can submit the form with the department_id included
            $.ajax({
                type: "POST",
                url: "studentApplication",
                data: $(this).serialize(), // Serialize the entire form data
                success: function (response) {
                    // Handle the response or redirect to another page as needed
                }
            });
        });

    </script>
</head>
<body>
<jsp:include page="header.jsp"/>
<br/>
<%--<div class="photo-upload">--%>
<%--    <label for="photo" class="photo-label">--%>
<%--        <img id="preview" src="placeholder-image.jpg" alt="Upload a photo">--%>
<%--        <div class="upload-icon">+</div>--%>
<%--    </label>--%>
<%--    <input type="file" id="photo" name="photo" accept="image/*" onchange="previewImage(this)">--%>
<%--</div>--%>

<form method="post" action="studentApplication" id="admissionForm">
    <input name="action" value="create_student" hidden>
    <fieldset>
        <legend>Personal Details</legend>
        <p>
            <label class="question" for="the_name">What is your name?</label>
            <input type="text" id="the_name" name="theName" placeholder="Enter your full name" size="50" required autofocus>
        </p>
        <p>
            <label class="question" for="the_email">What is your email address?</label>
            <input type="email" id="the_email" name="theEmail" placeholder="Enter your email" size="50" required>
        </p>
        <p>
            <label class="question" for="the_phone">What is your phone number?<br/></label>
            <input type="tel" id="the_phone" name="the_phone" placeholder="07XXXXXXXX" size="50" required>
        </p>
        <p>
            <label class="question" for="the_parent">What is your parent/guardian name?</label>
            <input type="text" id="the_parent" name="the_parent" placeholder="Enter full name" size="50" required>
        </p>
        <p>
            <label class="question" for="the_parent_phone">What is your parent/guardian phone number?<br/></label>
            <input type="tel" id="the_parent_phone" name="the_parent_phone" placeholder="07XXXXXXXX" size="50" required>
        </p>
        <p>
            <label class="question" for="the_dob">What is your date of birth?</label>
            <input type="date" id="the_dob" name="the_dob" placeholder="Enter your date of birth" size="50" required>
        </p>
<%--        <p>--%>
<%--            <label class="question" for="diploma_file">Upload Your A2 Diploma (PDF only):</label>--%>
<%--            <input type="file" id="diploma_file" name="diploma_file" accept=".pdf">--%>
<%--        </p>--%>
    </fieldset>
    <fieldset>
        <legend>Academic Programs</legend>
        <div>
       <input name="action" value="load_programme" hidden>
            <label class="question" for="the_dob">Choose Programme</label>
            <input type="hidden" name="action" value="selected_department">
            <select name="faculty_id" id="programme_select">
                <!-- Programme options will be populated dynamically using JavaScript -->
            </select>
            <label class="question" for="the_dob">Choose Faculty</label>
            <select name="faculty_id" id="faculty_Select">
                <!-- Faculty options will be populated dynamically using JavaScript -->
            </select>
            <label class="question" for="the_dob">Choose Department</label>
            <select name="department_id" id="department_select">
                <!-- Department options will be populated dynamically using JavaScript -->
            </select>
            <input type="hidden" name="department_id" id="selected_department_id" value="">
        </div>
    </fieldset>
    <fieldset>
        <legend>Survey Questions</legend>
        <p>
        <h3>Please fill free to answer the following questions</h3>
        <label for="the_invite"><span class="question">How did you know AUCA</span></label><br>
        <input type="text" id="the_invite" name="the_invite" placeholder="Type here" list="listOfBadChoices" size="50">
        </p>
        <p>
            <label for="how_like"><span class="question">How do you like AUCA?</span></label><br>
            <select id="how_like" name="how_like" size="4" multiple>
                <optgroup label="Not like">
                    <option value="Forced">I don't like. I'm forced by parents.</option>
                    <option value="Complicated Rules">I don't like some restrictions rules</option>
                </optgroup>
                <optgroup label="Likely">
                    <option value="Timetable">Good course timetable</option>
                </optgroup>
                <optgroup label="Preferred">
                    <option value="Skills">I like it's provided skills</option>
                </optgroup>
            </select>
        </p>
    </fieldset>
    <fieldset>
        <legend>Free for all!</legend>
        <p>
            <label for="message"><span class="question">Feel free to say something about AUCA, recommendations, etc.:</span></label>
            <textarea id="message" name="message" rows="7" cols="55"></textarea>
        </p>
    </fieldset>
    <div id="buttons">
        <input type="submit" value="Submit"> or
        <input type="reset" value="Erase">
    </div>
</form>
<datalist id="listOfBadChoices">
    <option value="from friends"></option>
    <option value="from website"></option>
    <option value="from parents"></option>
    <option value="I live nearby the campus"></option>
</datalist>
</div>
<%@ include file="footer.html" %>
</body>
</html>
