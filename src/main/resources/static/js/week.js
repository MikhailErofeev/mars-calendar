/**
 * @author m-erofeev
 * @since 12.04.14
 */
var days = ["Solis", "Lunae", "Martis", "Mercurii", "Jovis", "Veneris", "Saturni"]
function generateDaysAndScale() {
    var scale = $("<div class='scale'></div>");
    scale.append("<div class='scale-top'>day</div>")
    for (var i = 0; i < 24; i++) {
        var hour = i != 0 ? i + ":00" : "";
        var hourEl = $("<div class='scale-row'>" + hour + "</div>");
        scale.append(hourEl)
    }
    scale.append($("<div class='scale-row' style='height: 20px'>24:00</div>"))
    var $days = $("#days");
    $days.append(scale)
    for (var dayNumber = 0; dayNumber < 7; dayNumber++) {
        var day = $("<div class='day'></div>");
        day.append("<div class='day-top'>" + days[dayNumber] + "</div>")
        for (var i = 0; i < 24; i++) {
            var hour = $("<div class='hour'></div>");
            day.append(hour)
        }
        var hour = $("<div class='hour' style='height: 20px'></div>");
        day.append(hour)
        $days.append(day)
    }
}
$(document).ready(function () {
    generateDaysAndScale();
});