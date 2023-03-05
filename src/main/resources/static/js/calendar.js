var startDate = new Date();
var endDate = new Date();

startDate.setDate(startDate.getDate() + 2);
endDate.setDate(startDate.getDate() + 5);

var startYear = startDate.getFullYear();
var startMonth = ('0' + (startDate.getMonth() + 1)).slice(-2);
var startDay = ('0' + startDate.getDate()).slice(-2);

var endYear = endDate.getFullYear();
var endMonth = ('0' + (endDate.getMonth() + 1)).slice(-2);
var endDay = ('0' + endDate.getDate()).slice(-2);

var start = startYear + '-' + startMonth  + '-' + startDay;
var end = endYear + '-' + endMonth  + '-' + endDay;

document.addEventListener('DOMContentLoaded', function() {
        var calendarEl = document.getElementById('calendar');
        var calendar = new FullCalendar.Calendar(calendarEl, {
            selectable: true,
            height: 650,
            editable: false,
            dragScroll:false,
            unselectAuto:false,
            headerToolbar: {
            left:'',
            right:'',
            center:'title',
            <!-- right: 'dayGridMonth,timeGridWeek,timeGridDay'-->
        },

        dateClick: function(info) {
            if (info.dateStr <  start ||  info.dateStr >  end) {
                <!--        $(".day-highlight").removeClass("day-highlight");-->
                <!--        $(this).addClass("day-highlight");-->
                <!--        -->
                alert("You cannot book on this day!"  + info.dateStr);
            }else {
                alert("Excellent choice! We can book today.."  + info.dateStr);
            }
        }
    });
    calendar.render();
});
