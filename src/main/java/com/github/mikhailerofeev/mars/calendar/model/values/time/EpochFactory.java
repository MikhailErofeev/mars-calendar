package com.github.mikhailerofeev.mars.calendar.model.values.time;

import org.joda.time.DateTime;

/**
 * Created by Максим on 13.04.2014.
 */
public class EpochFactory {
    static public DateTime getDefaultEpoch(){
        return getMariner4FlyBy();
    }

    static public DateTime getMariner4FlyBy(){
        return new DateTime(1965, 7, 15, 1, 0);
    }
}
