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

var Day = Backbone.Model.extend({
    defaults: {
        "name": "Monday",
        "planet": "Earth",
        "year": 1,
        "month": 1,
        "day": 1
    }
});

var Week = Backbone.Collection.extend({
    mode: Day
});

var Planet = Backbone.Model.extend({
    defaults: {
        "days-of-week": ["d1", "d2", "d3", "d4"],
        "months": ["m1", "m2", "m3", "m4"],
        "new-month-new-week": true,
        "hours": 24,
        "minutes": 39,
        "name": "Earth"
    }
});

planets = {};
//@TODO
function getPlanetByName(name) {
    if (name in planets) {
        return planets[name];
    } else {
        var mars = new Planet();
        mars.fetch({url: "mars-example-planet.json", async: false});
        planets[name] = mars;
        return mars;
    }
}

var Month = Backbone.Model.extend({
    defaults: {
        "name": "January",
        "planetName": "Earth",
        "timestamp": 0,
        "year": 1,
        "month": 1,
        "days": 31
    },
    weeks: function () {
        var planet = getPlanetByName(this.get("planetName"));
        newWeek = planet.get("new-month-new-week");
        ret = [];
        week = new Week();
        if (newWeek) {
            for (i = 0; i < this.get("days"); i++) {
                if (i % 7 == 0) {
                    ret.push(week);
                    week = new Week();
                }
                var dayName = planet.get("days-of-week")[i % 7];
                var day = new Day({
                    name: dayName,
                    planet: planet.get("name"),
                    year: this.get("year"),
                    month: this.get("month"),
                    day: i
                });
                week.add(day);
            }
            ret.push(week)
        } else {
            console.err("unimplemented yet");
        }
        return ret;
    }
});

var LinkedMonth = Backbone.Model.extend({
    defaults: {
        "origin": null,
        "alternative": null
    }
});


var LinkedMonths = Backbone.Model.extend({
    defaults: {
        previous: null,
        current: null,
        next: null
    },

    parse: function (response) {
        ret = {};
        for (var key in this.defaults) {
            var embeddedData = response[key];
            el = new LinkedMonth(embeddedData);
            ret[key] = el;
            el.set({"origin": new Month(el.get("origin"))})
            el.set({"alternative": new Month(el.get("alternative"))})
        }
        return ret;
    }
});

function getSample() {
    sampleMonths = new LinkedMonths();
    sampleMonths.fetch({url: "mars-month-example.json", async: false});
    return sampleMonths
}


function getPrevCurrentAndNextWeeks(original, alternative, year, month, startDay) {
    var originMonth = getSample().get("current").get("origin");
    var weeks = originMonth.weeks();
    for (i = 0; i < weeks.length; i++) {
        var ret = null
        weeks[i].each(function (day) {
            if (year == day.get("year") && day.get("month") == month && day.get("day") == startDay) {
                ret = i;
            }
        });
        if (ret != null) {
            if (ret == 0) {
                var prevMonthWeeks = getSample().get("previous").get("origin").weeks();
                var lastWeekOfPrevMonth = prevMonthWeeks[prevMonthWeeks.length];
                return [lastWeekOfPrevMonth, weeks[ret], weeks[ret + 1]];
            } else if (ret == weeks.length - 1) {
                var nextMonthWeeks = getSample().get("next").get("origin").weeks();
                var firstWeekOfPrevMonth = nextMonthWeeks[0];
                return [weeks[ret - 1], weeks[ret], firstWeekOfPrevMonth];
            } else {
                return {"month": originMonth, "planet": getPlanetByName(originMonth.get("planet")),
                    "weeks": [weeks[ret - 1], weeks[ret], weeks[ret + 1]]};
            }
        }

    }
}