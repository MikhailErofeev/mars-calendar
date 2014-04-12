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
    $("#days-table").find("tbody").append(header);
    for (var hour = 0; hour < 24; hour++) {
        var row = $("<tr class='tr-hour'></tr>");
        var hourTxt = hour + ":00";
        row.append("<td class='scale-row'>" + hourTxt + "</td>")
        for (day = 0; day < 7; day++) {
            row.append("<td class='hour'></td>");
        }
        $("#days-table").find("tbody").append(row);
    }
}
$(document).ready(function () {
    generateDaysAndScale();
});