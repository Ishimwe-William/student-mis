    // Function to log out and invalidate the session
    function logoutAndInvalidateSession() {
    // Send an AJAX request to the server to invalidate the session
    let xhr = new XMLHttpRequest();
    xhr.open('GET', '/logout', true);
    xhr.send();

    // Redirect to the login page or any other desired page
    window.location.href = '/login.jsp';
}

    // Attach the function to the window's 'beforeunload' event
    window.addEventListener('beforeunload', logoutAndInvalidateSession);
