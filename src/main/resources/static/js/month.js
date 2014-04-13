/**
 * @author m-erofeev
 * @since 12.04.14
 */

function generateMonthTable(days, newWeekForMonth, prevMonthDays) {
    var $table = $("<table id='month-table'/>");
    var $tBody = $("<tbody/>");
    var $row;

    var rest = (7 - (days + prevMonthDays) % 7);
    var daysPlusNextAndPrev = newWeekForMonth ? days : (days + rest + prevMonthDays);
    for (var i = 0; i < daysPlusNextAndPrev; i++) {
        day = i + 1 - prevMonthDays;
        if (i % 7 == 0) {
            $tBody.append($row);
            $row = $("<tr/>");
        }
        preMonth = i < prevMonthDays;
        nextMonth = (i - prevMonthDays) >= days;
        link = "#!/week/mars/earth/2014/04/" + day;
        linkEl = "<a class='day-link' href='" + link + "'>" + day + "</a>";
        var td = $("<td class='month-day'>" + linkEl + "</td>");
        if (nextMonth || preMonth) {
            td.addClass("another-month");
        }
        $row.append(td);
    }
    if (newWeekForMonth) {
        if (rest % 7 != 0) {
            $row.append("<td class='next-month-new-week' colspan='" + rest + "'>next month start with new week</td>")
        }
    }
    $tBody.append($row);
    $table.append($tBody);
    return $table;
}

function generateMonthWithNewWeekStart(days) {
    return generateMonthTable(days, true, 0)
}

function generateMonthWithOutNewWeekStart(days, daysBefore) {
    return generateMonthTable(days, false, daysBefore)
}

$(document).ready(function () {
//    var weekTable = generateMonthWithOutNewWeekStart(28, 5);
    var weekTable = generateMonthWithNewWeekStart(29);
    $("#main").html(weekTable)
});