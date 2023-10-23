// document.getElementById("loginForm").addEventListener("submit", function (event) {
//   event.preventDefault();
//   let username = document.getElementById("txtUsername").value;
//   let password = document.getElementById("txtPassword").value;
//
//   if (username.trim() === "") {
//     alert("Please enter the username!");
//   } else if (password === "") {
//     alert("Please enter the password!");
//   } else {
//     // Create an object to hold the credentials
//     const credentials = {
//       username: username,
//       password: password
//     };
//
//     // Make a POST request to the /authentication servlet
//     fetch('/authentication', {
//       method: 'POST',
//       headers: {
//         'Content-Type': 'application/json'
//       },
//       body: JSON.stringify(credentials)
//     })
//         .then(response => {
//           if (response.ok) {
//             return response.json();
//           } else {
//             throw new Error('Authentication failed');
//           }
//         })
//         .then(data => {
//           // Handle the response from the servlet
//           if (data.success) {
//             alert("Login successful!");
//             window.location.href = "index.jsp"; // Redirect to a welcome page
//           } else {
//             alert("Authentication failed");
//           }
//         })
//         .catch(error => {
//           console.error('Error:', error);
//         });
//   }
// });
