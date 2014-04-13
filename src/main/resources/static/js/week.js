/**
 * @author m-erofeev
 * @since 12.04.14
 */
var days = ["Solis", "Lunae", "Martis", "Mercurii", "Jovis", "Veneris", "Saturni"]
function generateWeekTable(planetHours, planetMinutes, days) {
    var $table = $("<table id='days-table'/>");
    var tHead = $("<thead/>");
    var trHeader = $("<tr/>");
    tHead.append(trHeader);
    trHeader.append("<th class='scale-header'></th>")
    for (day = 0; day < 7; day++) {
        year = "0000";
        month = "00";
        dayHeader = days[day] + " <br/>" + [year,month,day+1].join("-");
        trHeader.append("<th class='day-header'>" +dayHeader +  "</th>");
    }
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