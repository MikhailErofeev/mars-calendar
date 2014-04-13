/**
 * @author m-erofeev
 * @since 12.04.14
 */

var Controller = Backbone.Router.extend({
    routes: {
        "": "week",
        "!/": "week"
    },

    week: function () {
        r.navigate('/week/mars/earth/2014/12/13', { trigger: true });
    },

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
        var router = this,
            routes = [
                [ /^!\/week\/([a-z]+)\/([a-z]+)\/(\d+)\/(\d+)\/(\d+)\/?$/, "week", this.week],
                [ /^!\/month\/([a-z]+)\/(\d+)\/(\d+)\/?$/, "month", this.month],
                [ /^!\/year\/([a-z]+)\/(\d+)\/?$/, "year", this.year]
            ];
        _.each(routes, function (route) {
            router.route.apply(router, route);
        });
    }
});

$(document).ready(function () {
    var controller = new Controller();
    Backbone.history.start();
});