/**
 * Created by Владимир on 12.04.2014.
 */

function TaskTime(day, hour, quarter) {
    this.day = day;
    this.hour = hour;
    this.quarter = quarter;
}

function Task(name, desc, begin, end, id, isOtherPlanetTime) {
    this.id = ((id === undefined || id === null) ? Task.id++ : id);
    this.name = name;
    this.desc = desc;
    this.begin = begin;
    this.end = end;

    this.isPartial = false;
    this.isPartialBegin = true;
    this.isOtherPlanetTime = (true === isOtherPlanetTime);

    this.container = "<div></div>";

    function FindCell(table, taskTime) {
        var template = "td[hour=@hour][day=@day][quarter=@quarter]";
        return $(template
            .replace('@hour', taskTime.hour)
            .replace('@day', taskTime.day)
            .replace('@quarter', taskTime.quarter),
            table);
    }

    this.Draw = function(table) {
        var beginCell = FindCell(table, this.begin);
        var endCell = FindCell(table, this.end);

        this.isPartial = (this.begin.day != this.end.day);
        if (!this.isPartial && (beginCell.length == 0 || endCell.length == 0)) return;

        var beginPosition = beginCell.offset();
        var endPosition = endCell.offset();

        var drawable = $(this.container)
            .attr('id', this.id)
            .append(this.name)
            .addClass('task');

        if (this.isOtherPlanetTime)
            drawable.addClass('otherPlanetTime');

        if (this.isPartial) {
            if (endPosition === undefined)
                endPosition = { top: 0 };
            endPosition.top = table.height() + table.offset().top;
            drawable.addClass('partial-begin');
        }

        var height = endPosition.top - beginPosition.top - 4;
        var width = beginCell.width() - 4;

        drawable.height(height);
        drawable.width(width);

        beginCell.append(drawable);

        // draw second part if needeed
        if (!this.isPartial) return;

        beginCell = FindCell(table, new TaskTime(this.end.day, 0, 0));

        if (beginCell.length == 0) return;

        beginPosition = beginCell.offset();
        endPosition = endCell.offset();

        drawable = $(this.container)
            .attr('id', this.id)
            .append(this.name)
            .addClass('task')
            .addClass('partial-end');

        if (this.isOtherPlanetTime)
            drawable.addClass('otherPlanetTime');

        height = endPosition.top - beginPosition.top;
        width = beginCell.width() - 4;

        drawable.height(height);
        drawable.width(width);

        beginCell.append(drawable);
    }
}

Task.id = 0;