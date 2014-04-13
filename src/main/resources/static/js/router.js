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
    },

    month: function (origin, year, month) {
        AppState.originPlanet = origin;
        AppState.year = year;
        AppState.month = month;
    },

    year: function (origin, year) {
        AppState.originPlanet = origin;
        AppState.year = year;
    },

    error: function () {
        console.log("error")
    },

    initialize: function () {
//        "": "start", // Пустой hash-тэг
//        "!/": "start", // Начальная страница
//        "!/week1": "success", // Блок удачи
        var router = this,
            routes = [
                [ /^!\/week\/([a-z]+)\/([a-z]+)\/(\d+)\/(\d+)\/(\d+)$/, "week", this.week],
                [ /^!\/month\/([a-z]+)\/(\d+)\/(\d+)$/, "month", this.month],
                [ /^!\/year\/([a-z]+)\/(\d+)$/, "year", this.year]
//        "!/month/:year-:mm": "month",
//        "!/year/:year": "year",
//        "!/error": "error" ]// Блок ошибки
            ];
        _.each(routes, function (route) {
            router.route.apply(router, route);
            // ^ Say it ten times fast
        });
    }
});

var controller = new Controller(); // Создаём контроллер
//controller.initialize()

Backbone.history.start();  // Запускаем HTML5 History push    