// Get a reference to the scroll-to-top button
const scrollBtn = document.getElementById("scrollBtn");

// Add a scroll event listener to show/hide the button
window.addEventListener("scroll", () => {
    if (window.scrollY > 20) { // You can adjust this value to determine when the button appears
        scrollBtn.classList.add("visible");
    } else {
        scrollBtn.classList.remove("visible");
    }
});

// Add a click event listener to scroll back to the top when the button is clicked
scrollBtn.addEventListener("click", () => {
    window.scrollTo({
        top: 0,
        behavior: "smooth" // Smooth scrolling animation
    });
});
