/**
 * @author m-erofeev
 * @since 12.04.14
 */

var Controller = Backbone.Router.extend({
    routes: {},

    week: function (origin, alternative, year, month, day) {
        appState.set({
            originPlanet: origin,
            alternativePlanet: alternative,
            year: year,
            month: month,
            day: day,
            viewType: "week"
        });
    },

    month: function (origin, year, month) {
        appState.set({
            originPlanet: origin,
            year: year,
            month: month,
            viewType: "month"
        });
    },

    year: function (origin, year) {
        appState.set({
            originPlanet: origin,
            year: year,
            viewType: "year"
        });
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
                [ /^!\/week\/([a-z]+)\/([a-z]+)\/(\d+)\/(\d+)\/(\d+)\/?$/, "week", this.week],
                [ /^!\/month\/([a-z]+)\/(\d+)\/(\d+)\/?$/, "month", this.month],
                [ /^!\/year\/([a-z]+)\/(\d+)\/?$/, "year", this.year]
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
    Backbone.history.start();
});