/**
 * Created by Владимир on 12.04.2014.
 */

function TaskTime(day, hour, quarter) {
    this.day = day;
    this.hour = hour;
    this.quarter = quarter;
}

function Task(name, desc, begin, end) {
    this.name = name;
    this.desc = desc;
    this.begin = begin;
    this.end = end;
    this.container = "<div class='task'>@data</div>";

    function FindCell(table, taskTime) {
        return $("td[hour=" + taskTime.hour +
            "][day=" + taskTime.day +
            "][quarter=" + taskTime.quarter +
            "]", table);
    }

    this.Draw = function(table) {
        var beginCell = FindCell(table, this.begin);
        var endCell = FindCell(table, this.end);
        if (beginCell.length == 0 || endCell.length == 0) return;
        var drawable = $(this.container.replace('@data', this.name));
        var height = endCell.offset().top - beginCell.offset().top;
        drawable.height(height);
        drawable.width(beginCell.width() - 4);
        beginCell.append(drawable);
    }
}