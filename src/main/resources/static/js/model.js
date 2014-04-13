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
