function previewImage(input) {
    let preview = document.getElementById('preview');
    if (input.files && input.files[0]) {
        let reader = new FileReader();
        reader.onload = function (e) {
            preview.src = e.target.result;
        };
        reader.readAsDataURL(input.files[0]);
    } else {
        preview.src = 'placeholder-image.jpg'; // Display a default image if no file is selected
    }
}

function validateForm() {
    let name = document.getElementById('the_name').value;
    let email = document.getElementById('the_email').value;
    let phone = document.getElementById('the_phone').value;


    if (name === "" || email === "" || phone === "") {
        alert("Please fill in all required fields.");
        return false; // Prevent form submission
    }

    return true; // Allow form submission
}
 // Function to validate the diploma file
    function validateDiplomaFile() {
    let diplomaFileInput = document.getElementById('diploma_file');
    let fileName = diplomaFileInput.value;
    let allowedExtensions = /(\.pdf)$/i; // Accept only PDF files

    if (!allowedExtensions.exec(fileName)) {
    alert('Please upload a PDF file.');
    diplomaFileInput.value = ''; // Clear the input field
    return false; // Prevent form submission
}

    return true; // Allow form submission
}

    // Function to validate the entire form
    function validateForm() {
    let name = document.getElementById('the_name').value;
    let email = document.getElementById('the_email').value;
    let phone = document.getElementById('the_phone').value;

    // Add more validation as needed

    // Example: Check if name, email, and phone are not empty
    if (name === "" || email === "" || phone === "") {
    alert("Please fill in all required fields.");
    return false; // Prevent form submission
}

    // Validate the diploma file
    return validateDiplomaFile();
}