document.getElementById("myForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent the default form submission

    const formData = new FormData(this); // Create a FormData object with form data

    fetch("YourServletURL", {
        method: "POST",
        body: formData
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Network response was not ok");
            }
            return response.text();
        })
        .then(responseText => {
            // Handle the response from the servlet
            console.log(responseText);
        })
        .catch(error => {
            console.error("Fetch error:", error);
        });
});
