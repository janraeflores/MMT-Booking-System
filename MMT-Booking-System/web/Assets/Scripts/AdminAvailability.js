function disableTime(){
    var timeStart = document.getElementById("timeStart");
    var timeEnd = document.getElementById("timeEnd");
    var check = document.getElementById("allDay");
    
    if(check.checked){
        timeStart.disabled = true;
        timeEnd.disabled = true;
    } else{
        timeStart.disabled = false;
        timeEnd.disabled = false;
    }
}