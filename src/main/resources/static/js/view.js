var Main = Backbone.View.extend({

    model: appState,

    initialize: function () {
        this.model.bind('change', this.render, this);
    },

    render: function () {
        console.log(this.model.get("viewType"));
        if (this.model.get("viewType") == "month") {
            var monthTable = generateMonthWithNewWeekStart(29);
            $("#main").html(monthTable)
        } else if (this.model.get("viewType") == "week") {
            var weekTable = generateWeekTable(24, 0, ["Su", "Mn", "Tu", "We", "Th", "Fr", "Sa"]);
            $("#main").html(weekTable)

            //---------- tasks tests -----------------------
            var tasks = [
                new Task("task1", "task1", new TaskTime(3, 0, 0), new TaskTime(3, 20, 3))
            ];

            for (var taskIndex in tasks) {
                tasks[taskIndex].Draw(weekTable);
            }
            //-------- end tasks tests ---------------------
        }
        return this;
    }

});

var main = new Main();