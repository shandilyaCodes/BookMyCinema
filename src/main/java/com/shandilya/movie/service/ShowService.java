package com.shandilya.movie.service;

import com.shandilya.movie.exceptions.NotFoundException;
import com.shandilya.movie.model.Movie;
import com.shandilya.movie.model.Screen;
import com.shandilya.movie.model.Show;

import java.util.*;

public class ShowService {

    private final Map<String, Show> shows;

    public ShowService() {
        this.shows = new HashMap<>();
    }

    public Show getShow(String showId) {
        if (!shows.containsKey(showId)) {
            throw new NotFoundException("Show not found!");
        }
        return shows.get(showId);
    }

    public Show createShow(Movie movie, Screen screen, Date startTime, Integer duration) {
        final String showId = UUID.randomUUID().toString();
        Show show = new Show(showId, movie, screen, startTime, duration);
        shows.put(showId, show);
        return show;
    }

    private List<Show> getShowsForScreen(Screen screen) {
        List<Show> showList = new ArrayList<>();
        for (Show show : shows.values()) {
            if (show.getScreen().equals(screen)) {
                showList.add(show);
            }
        }
        return showList;
    }
}