package com.shandilya.movie.model;

import lombok.Getter;
import lombok.NonNull;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Theater {
    private final String theaterId;
    private final String theaterName;
    private final List<Screen> screens;

    public Theater(String theaterId, String theaterName) {
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        screens = new ArrayList<>();
    }

    public void addScreen(@NonNull final Screen screen) {
        screens.add(screen);
    }
}