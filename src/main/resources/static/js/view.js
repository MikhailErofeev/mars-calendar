var Main = Backbone.View.extend({

    model: appState,

    initialize: function () {
        this.model.bind('change', this.render, this);
    },

    initLinks: function () {
        $('#month-btn').attr('href', "#!/month/" + this.model.get("originalPlanet") +
            "/" + this.model.get("year") + "/" + this.model.get("month"));

        $('#year-btn').attr('href', "#!/month/" + this.model.get("originalPlanet") + "/" + this.model.get("year"));

        $('#week-btn').attr('href', "#!/month/" + this.model.get("originalPlanet") + "/" + this.model.get("alternativePlanet") +
            "/" + this.model.get("year") + "/" + this.model.get("month") + "/" + this.model.get("day"));

        $('#today-btn').attr('href', "#!/month/" + this.model.get("originalPlanet") + "/" + this.model.get("alternativePlanet") +
            "/" + this.model.get("year") + "/" + this.model.get("month") + "/" + this.model.get("day"));

    },
    initWeekLinks: function (monthPlanetAndWeeks) {
        var year = monthPlanetAndWeeks.month.get("year");
        var month = monthPlanetAndWeeks.month.get("month");
        var lastWeekFirstDay = monthPlanetAndWeeks.weeks[0].get("0");
        console.log(lastWeekFirstDay);
        console.log(monthPlanetAndWeeks.weeks[0]);
        console.log(monthPlanetAndWeeks.weeks[1]);
        console.log(monthPlanetAndWeeks.weeks[2]);
        var nextWeekFirstDay = monthPlanetAndWeeks.weeks[2].get("0");
        this.initLinks();
        $('#prev-week-btn').attr('href', "#!/month/" + this.model.get("originalPlanet") + "/" + this.model.get("alternativePlanet") +
            "/" + this.model.get("year") + "/" + this.model.get("month") + "/" + lastWeekFirstDay.get("day"));
        $('#next-week-btn').attr('href', "#!/month/" + this.model.get("originalPlanet") + "/" + this.model.get("alternativePlanet") +
            "/" + this.model.get("year") + "/" + this.model.get("month") + "/" + nextWeekFirstDay.get("day"));
    },
    render: function () {
        console.log(this.model.get("viewType"));
        if (this.model.get("viewType") == "month") {
            var monthTable = generateMonthWithNewWeekStart(29);
            $("#main").html(monthTable)
        } else if (this.model.get("viewType") == "week") {
            var monthPlanetAndWeeks = getPrevCurrentAndNextWeeks(this.model.get("originalPlanet"), this.model.get("alternativePlanet"),
                this.model.get("year"), this.model.get("month"), this.model.get("day"));
            var weekTable = generateWeekTable(monthPlanetAndWeeks);
            this.initWeekLinks(monthPlanetAndWeeks);
            $("#main").html(weekTable);

            //---------- tasks tests -----------------------
            var tasks = [
                new Task("", "", new TaskTime(0, 3, 0), new TaskTime(1, 2, 0), null, true),
                new Task("task1", "task1", new TaskTime(2, 0, 0), new TaskTime(2, 16, 3)),
                new Task("task2", "task2", new TaskTime(2, 4, 0), new TaskTime(2, 19, 0))
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