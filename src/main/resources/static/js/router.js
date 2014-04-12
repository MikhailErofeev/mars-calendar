/**
 * @author m-erofeev
 * @since 12.04.14
 */


var Controller = Backbone.Router.extend({
    routes: {"!/": "error" // Начальная страница
    },
    week: function (origin, alternative, year, month, day) {
        console.log(origin, alternative, year, month, day);
    },

    month: function (origin, year, mm) {
        console.log(year, mm);
    },

    year: function (origin, year) {
        console.log(year);
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