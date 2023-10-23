<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Admission Form</title>
    <meta content="width=device-width, initial-scale=1" name="viewport">
    <link rel="stylesheet" href="static/css/index.css">
    <script src="static/js/index.js"></script>
    <script src="static/js/logoutOnExit.js"></script>
</head>
<body>
<jsp:include page="header.jsp"/>
<br/>
<div class="photo-upload">
    <label for="photo" class="photo-label">
        <img id="preview" src="placeholder-image.jpg" alt="Upload a photo">
        <div class="upload-icon">+</div>
    </label>
    <input type="file" id="photo" name="photo" accept="image/*" onchange="previewImage(this)">
</div>
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
        <p>
            <label class="question" for="diploma_file">Upload Your A2 Diploma (PDF only):</label>
            <input type="file" id="diploma_file" name="diploma_file" accept=".pdf">
        </p>
    </fieldset>
    <fieldset>
        <legend>Academic Programs</legend>
        <h3>Business Department</h3>
        <p>
            <span class="question"><i>Choose a Faculty within Business Department:</i></span><br>
        <hr>
        <span id="business">
            <input type="radio" id="business_accounting" name="majorFac" value="Accounting">
            <label for="business_accounting">Accounting</label>

            <input type="radio" id="business_management" name="majorFac" value="Management">
            <label for="business_management">Management</label>

            <input type="radio" id="business_finance" name="majorFac" value="Finance">
            <label for="business_finance">Finance </label>

            <input type="radio" id="business_marketing" name="majorFac" value="Marketing">
            <label for="business_marketing">Marketing </label>
        </span>
        </p>

        <h3>IT Department</h3>
        <p>
            <span class="question"><i>Choose a Faculty within IT Department:</i></span><br>
        <hr>
        <span id="IT">
            <input type="radio" id="it_software" name="majorFac" value="Software Engineering">
            <label for="it_software">Software Engineering</label>

            <input type="radio" id="it_network" name="majorFac" value="Networks and Communications Systems">
            <label for="it_network">Networks and Communications Systems</label>

            <input type="radio" id="it_info" name="majorFac" value="Information Management">
            <label for="it_info">Information Management </label>
        </span>
        </p>
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
