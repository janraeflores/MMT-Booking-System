const calendar = document.querySelector(".calendar"),
    date = document.querySelector(".date"),
    daysContainer = document.querySelector(".days"),
    prev = document.querySelector(".prev"),
    next = document.querySelector(".next"),
    todayButton = document.querySelector(".today-btn"),
    goToButton = document.querySelector(".goto-btn"),
    dateInputed = document.querySelector(".date-input");

const months = [
    "January",
    "February",
    "March",
    "April",
    "May",
    "June",
    "July",
    "August",
    "September",
    "October",
    "November",
    "December"
];

let today = new Date();
let activeDay;
let month = today.getMonth();
let year = today.getFullYear();



// Add Days Function
function initializeCalendar() {
    // get previous month days, all days of current month, and next months days
    const firstDay = new Date(year, month, 1);
    const lastDay = new Date(year, month + 1, 0);
    const prevLastDay = new Date(year, month, 0);
    const prevDays = prevLastDay.getDate();
    const lastDate = lastDay.getDate();
    const day = firstDay.getDay();
    const nextDays = 7 - lastDay.getDay() - 1;

    // Update month at top of calendar
    date.innerHTML = months[month] + " " + year;

    // add days
    let days = "";

    // get previous month days
    for (let p = day; p > 0; p--) {
        days += `<div class="day prev-date">${prevDays - p + 1}</div>`;
    }

    //get current month days
    for (let x = 1; x <= lastDate; x++) {

        // grab current date (for today class)
        if (
            x === new Date().getDate() &&
            year === new Date().getFullYear() &&
            month === new Date().getMonth()
        ) {
            days += `<div class="day today">${x}</div>`;
        } else {
            // get remaining current days
            days += `<div class="day today">${x}</div>`;
        }
    }

    // get next month days
    for (let n = 1; n <= nextDays; n++) {
        days += `<div class="day next-date">${n}</div>`;
    }

    daysContainer.innerHTML = days;
    addListner();
}

initializeCalendar();


// Previous Month Function
function previousMonth() {
    month--;
    if (month < 0) {
        month = 11;
        year--;
    }
    initializeCalendar();
}

// Next Month Function
function nextMonth() {
    month++;
    if (month > 11) {
        month = 0;
        year++;
    }
    initializeCalendar();
}

// Event Listener for previous / next button
prev.addEventListener("click", previousMonth);
next.addEventListener("click", nextMonth);

// Today Button Functionality
todayButton.addEventListener("click", () => {
    today = new Date();
    month = today.getMonth();
    year = today.getFullYear();
    initializeCalendar();
});

// Search Date Functionality
dateInputed.addEventListener("input", (e) => {

    // numbers only allowed
    dateInputed.value = dateInputed.value.replace(/[^0-9/]/g, "");
    if (dateInputed.value.length === 2) {
        // add slash automatically if two numbers are entered
        dateInputed.value += "/";
    }
    if (dateInputed.value.length > 7) {
        // no more than 7 inputs allowed
        dateInputed.value = dateInputed.value.slice(0, 7);
    }

    // allow user to re-enter date
    if (e.inputType === "deleteContentBackward") {
        if (dateInputed.value.length === 3) {
            dateInputed.value = dateInputed.value.slice(0, 2);
        }
    }
});

goToButton.addEventListener("click", gotoDate);

function gotoDate() {
    console.log("here");
    const dateArr = dateInputed.value.split("/");
    if (dateArr.length === 2) {
        if (dateArr[0] > 0 && dateArr[0] < 13 && dateArr[1].length === 4) {
            month = dateArr[0] - 1;
            year = dateArr[1];
            initializeCalendar();
            return;
        }
    }
    alert("Invalid Date");
}

function addListner() {
    const days = document.querySelectorAll(".day");
    days.forEach((day) => {
        day.addEventListener("click", (e) => {
            activeDay = Number(e.target.innerHTML);
            getCurrentDate(e.target.innerHTML);

            //resets chosen date (hover/color in effect)
            days.forEach((day) => {
                day.classList.remove("active");
            });

            // If previous month day is clicked within the current month view, go to previous month and add active
            if (e.target.classList.contains("prev-date")) {
                previousMonth();
                setTimeout(() => {
                    const days = document.querySelectorAll(".day");

                    // After going to previous month, active added to clicked date
                    days.forEach((day) => {
                        if (
                            !day.classList.contains("prev-date") &&
                            day.innerHTML === e.target.innerHTML
                        ) {
                            day.classList.add("active");
                        }
                    });
                }, 100);
            } else if (e.target.classList.contains("next-date")) {
                nextMonth();
                // Same deal as above, isntead for next month
                setTimeout(() => {
                    const days = document.querySelectorAll(".day");
                    days.forEach((day) => {
                        if (
                            !day.classList.contains("next-date") &&
                            day.innerHTML === e.target.innerHTML
                        ) {
                            day.classList.add("active");
                        }
                    });
                }, 100);
            } else {
                e.target.classList.add("active");
            }
        });
    });
}

let selectedDate;

// Active Date Functionality (date title on right side)
const eventDay = document.querySelector(".event-day"),
    eventDate = document.querySelector(".event-date");

function getCurrentDate(date) {
    const day = new Date(year, month, date);
    const dayName = day.toString().split(" ")[0];
    eventDay.innerHTML = dayName;
    eventDate.innerHTML = date + " " + months[month] + ", " + year;


    /*
    // Grab selected date here and push to java???
    let selectedDate = date + " " + months[month] + " " + year;

    // Current print out is day-month-year e.g. 4 February 2023
    console.log(selectedDate);

    let ajax = new XMLHttpRequest();
    ajax.open("GET", "<URL>?selectedDate" + selectedDate, true)
    ajax.send();

    // Then in Java we do something like this???
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String selectedDate = request.getParameter("selectedDate");
        // process the selected date as needed...
    }
    */
}


