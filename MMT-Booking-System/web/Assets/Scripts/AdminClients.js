// Displays a message for when a user is deleted for 4 seconds, then removes it

document.getElementById("deleted-client").style.display = "block";

setTimeout(function() {
    document.getElementById("deleted-client").style.display = "none";
    
}, 4000);
