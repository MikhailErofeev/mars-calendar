/**
 * @author m-erofeev
 * @since 12.04.14
 */


var Controller = Backbone.Router.extend({
    routes: {
//        "": "start", // Пустой hash-тэг
//        "!/": "start", // Начальная страница
//        "!/week1": "success", // Блок удачи
        "!/week/:year-:mm-:dd": "week",
        "!/month/:year-:mm": "month",
        "!/year/:year": "year",
        "!/error": "error" // Блок ошибки
    },

    week: function (year, mm, dd) {
        console.log(year, mm, dd);
    },

    month: function (year, mm) {
        console.log(year, mm);
    },

    year: function (year) {
        console.log(year);
    },

    error: function () {
        console.log("error")
    }
});

var controller = new Controller(); // Создаём контроллер

Backbone.history.start();  // Запускаем HTML5 History push    