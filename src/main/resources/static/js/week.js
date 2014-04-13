/**
 * @author m-erofeev
 * @since 12.04.14
 */
var days = ["Solis", "Lunae", "Martis", "Mercurii", "Jovis", "Veneris", "Saturni"]
function generateDaysAndScale(planetHours, planetMinutes) {
    var header = $("<tr></tr>");
    header.append("<th class='scale-header'></th>")
    for (day = 0; day < 7; day++) {
        header.append("<th class='day-header'>" + days[day] + "</th>");
    }
    $("#days-table").find("thead").append(header);
    ;
    var planetQuarters = Math.ceil((planetHours * 60 + planetMinutes) / 15.0);
    console.log(planetQuarters)
    for (var quarter = 0; quarter < planetQuarters; quarter++) {
        hour = quarter / 4;
        var row = $("<tr class='tr-hour'></tr>");
        if (quarter % 4 == 0) {
            hourTxt = hour + ":00";
            row.append("<td rowspan='4' class='scale-row'>" + hourTxt + "</td>")
        }

        for (day = 0; day < 7; day++) {
            var $td = $("<td class='quarter'></td>");
            if (quarter % 4 == 3) {
                $td.addClass("hour");
            }
            row.append(
                $td.attr("hour", hour).attr("day", day).attr("quarter", quarter % 4));
        }
        $("#days-table").find("tbody").append(row);
    }
}
$(document).ready(function () {
//    generateDaysAndScale(24, 0);
    generateDaysAndScale(24, 37);
});