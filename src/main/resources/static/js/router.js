/**
 * @author m-erofeev
 * @since 12.04.14
 */

var Controller = Backbone.Router.extend({
    routes: {"!/": "error" // Начальная страница
    },
    week: function (origin, alternative, year, month, day) {
        AppState.originPlanet = origin;
        AppState.alternativePlanet = alternative;
        AppState.year = year;
        AppState.month = month;
        AppState.day = day;
        var weekTable = generateWeekTable(24, 0, ["Su", "Mn", "Tu", "We", "Th", "Fr", "Sa"]);
        $("#main").html(weekTable)
    },

    month: function (origin, year, month) {
        AppState.originPlanet = origin;
        AppState.year = year;
        AppState.month = month;
        var monthTable = generateMonthWithNewWeekStart(29);
        $("#main").html(monthTable)
    },

    year: function (origin, year) {
        AppState.originPlanet = origin;
        AppState.year = year;
    },

    error: function () {
        console.log("error")
    },

    initialize: function () {
//        "": "start", 
//        "!/": "start",
//        "!/week1": "success", 
        var router = this,
            routes = [
                [ /^!\/week\/([a-z]+)\/([a-z]+)\/(\d+)\/(\d+)\/(\d+)$/, "week", this.week],
                [ /^!\/month\/([a-z]+)\/(\d+)\/(\d+)$/, "month", this.month],
                [ /^!\/year\/([a-z]+)\/(\d+)$/, "year", this.year]
//        "!/month/:year-:mm": "month",
//        "!/year/:year": "year",
//        "!/error": "error" ]
            ];
        _.each(routes, function (route) {
            router.route.apply(router, route);
            // ^ Say it ten times fast
        });
    }
});

$(document).ready(function () {
    var controller = new Controller();
//controller.initialize()

    Backbone.history.start();
});