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
                row = $("<tr class='hour' style='height: 5px;'></tr>");
            else
                row = $("<tr class='hour'></tr>");

            var hourTxt = "";
            if (quarter == 0)
                hourTxt = hour + ":00";

            row.append("<td class='scale-row'>" + hourTxt + "</td>")
            for (day = 0; day < 7; day++) {
                row.append(
                        $("<td class='hour'></td>")
                            .attr("hour", hour)
                            .attr("day", day)
                            .attr("quarter", quarter));
            }
            $("#days-table").find("tbody").append(row);
        }
        
        var hourTxt = hour + ":00";
        row.append("<td class='scale-row'>" + hourTxt + "</td>")
        for (day = 0; day < 7; day++) {
            row.append("<td class='hour' hour=" + hour + " day=" + day + "></td>");
        }
        $("#days-table").find("tbody").append(row);
    }
}
$(document).ready(function () {
    generateDaysAndScale();
});