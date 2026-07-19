document.addEventListener('DOMContentLoaded', function () {

    const calendarEl = document.getElementById('calendar');

    if (!calendarEl) {
        return;
    }

    const calendar = new FullCalendar.Calendar(calendarEl, {

    locale: 'id',

    buttonText: {
        today: 'Hari Ini',
        month: 'Bulan',
        week: 'Minggu',
        day: 'Hari'
    },

    headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },

    selectable: true,
    navLinks: true,

    events: "/api/events",

    dateClick(info) {
        alert("Tanggal dipilih : " + info.dateStr);
    },

    eventClick(info) {

        alert(
            "Event : " + info.event.title +
            "\nTanggal : " +
            info.event.start.toLocaleDateString("id-ID")
        );

    }

});

    calendar.render();

});