
function displayInfo() {
    var x = document.getElementById("appointInfo");
    var y = document.getElementById("displaySymbol");

//    console.log("AppointInfo = " + document.getElementById("appointInfo").value + " appoint = " + document.getElementById("appoint").value);

    if (x.style.display === "none") {
        x.style.display = "block";
        y.value = "˄";
    } else {
        x.style.display = "none";
        y.value = "˅";
    }
}


