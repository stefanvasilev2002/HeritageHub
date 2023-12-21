
    var contactButton = document.getElementById('contactButton');
    var contactNavItem = document.getElementById('contactNavItem');

    // Function to toggle visibility based on screen size
    function toggleVisibility() {
    if (window.innerWidth < 768) { // Adjust breakpoint as needed
    contactNavItem.style.display = 'inline-block';
    contactButton.style.display = 'none';
} else {
    contactNavItem.style.display = 'none';
    contactButton.style.display = 'inline-block';
}
}

    // Initial check on page load
    toggleVisibility();

    // Event listener for window resize
    window.addEventListener('resize', toggleVisibility);
