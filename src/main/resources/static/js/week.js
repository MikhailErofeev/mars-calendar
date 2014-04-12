/**
 * @author m-erofeev
 * @since 12.04.14
 */
var days = ["Solis", "Lunae", "Martis", "Mercurii", "Jovis", "Veneris", "Saturni"]
function generateDaysAndScale() {
    var header = $("<tr></tr>");
    header.append("<th class='scale-header'></th>")
    for (day = 0; day < 7; day++) {
        header.append("<th class='day-header'>" + days[day] + "</th>");
    }
    $("#days-table").find("thead").append(header);
    for (var hour = 0; hour < 25; hour++) {
        for (var quarter = 0; quarter < 4; quarter++) {
            var row;
            if (hour == 24 && quarter == 3)
                row = $("<tr class='tr-hour' style='height: 5px;'></tr>");
            else
                row = $("<tr class='tr-hour'></tr>");

            if (quarter == 0) {
                hourTxt = hour + ":00";
                row.append("<td rowspan='4' class='scale-row'>" + hourTxt + "</td>")
            } 

            for (day = 0; day < 7; day++) {
                var $td = $("<td class='quarter'></td>");
                if (quarter == 3) {
                    $td.addClass("hour");
                }
                row.append(
                    $td.attr("hour", hour).attr("day", day).attr("quarter", quarter));
            }
            $("#days-table").find("tbody").append(row);
        }
    }
}
$(document).ready(function () {
    generateDaysAndScale();
});