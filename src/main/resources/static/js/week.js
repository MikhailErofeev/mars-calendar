/**
 * @author m-erofeev
 * @since 12.04.14
 */
function generateWeekTable(monthPlanetAndWeeks) {
    planetHours = monthPlanetAndWeeks.planet.get("hours");
    planetMinutes = monthPlanetAndWeeks.planet.get("minutes");
    days = monthPlanetAndWeeks.planet.get("days-of-week");
    currentWeek = monthPlanetAndWeeks.weeks[1];
    var $table = $("<table id='days-table'/>");
    var tHead = $("<thead/>");
    var trHeader = $("<tr/>");
    tHead.append(trHeader);
    trHeader.append("<th class='scale-header'></th>")
    currentWeek.each(function (day) {
        year = day.get("year");
        month = day.get("month");
        dayHeader = day.get("name") + " <br/>" + [year, month, day.get("day") + 1].join("-");
        trHeader.append("<th class='day-header'>" + dayHeader + "</th>");
    });
    $table.append(tHead);
    var planetQuarters = Math.ceil((planetHours * 60 + planetMinutes) / 15.0);
    $body = $("<tbody/>");
    for (var quarter = 0; quarter < planetQuarters; quarter++) {
        var hour = Math.floor(quarter / 4);
        var row = $("<tr class='tr-hour'/>");
        if (quarter % 4 == 0) {
            row.append("<td rowspan='4' class='scale-row'>" + (hour + ":00") + "</td>")
        }
        for (var day = 0; day < 7; day++) {
            var $td = $("<td class='quarter'></td>");
            if (quarter % 4 == 3) {
                $td.addClass("hour");
            }
            row.append($td.attr("hour", hour).attr("day", day).attr("quarter", quarter % 4));
        }
        $body.append(row);
    }
    $table.append($body);
    return $table;
}