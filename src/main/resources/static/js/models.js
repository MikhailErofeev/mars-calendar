var AppState = Backbone.Model.extend({
    defaults: {
        originPlanet: "",
        alternativePlanet: "",
        viewType: "",
        year: 0,
        month: 0,
        week: 0
    }
});

var appState = new AppState();

var Planet = Backbone.Model.extend({
    defaults: {
        "days-of-week": ["d1", "d2", "d3", "d4"],
        "months": ["m1", "m2", "m3", "m4"],
        "new-month-new-week": true,
        "hours": 24,
        "minutes": 39
    }
});

mars = new Planet();
mars.fetch({url: "mars-example-planet.json"})


var Month = Backbone.Model.extend({
    defaults: {
        "name": "January",
        "planet": "Earth",
        "earth-timestamp": 0,
        "year": 1,
        "month": 1,
        "days": 31
    }
});


var Week = Backbone.Model.extend({
    defaults: {
        "name": "Monday",
        "planet": "Earth",
        "earth-timestamp": 0,
        "year": 1,
        "month": 1,
        "day": 1
    }
});

var Weeks = Backbone.Collection.extend({
    mode: Week
});