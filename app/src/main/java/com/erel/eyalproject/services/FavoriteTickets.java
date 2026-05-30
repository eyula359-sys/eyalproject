package com.erel.eyalproject.services;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

public class FavoriteTickets {

    private static final String PREFS_NAME = "favorites_prefs";
    private static final String KEY_FAVORITES = "favorite_ticket_ids";

    private final SharedPreferences prefs;

    public FavoriteTickets(Context context) {
        prefs = context.getApplicationContext()
                .getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
    }

    public boolean isFavorite(String ticketId) {
        return getFavoriteIds().contains(ticketId);
    }

    public void addFavorite(String ticketId) {
        Set<String> ids = new HashSet<>(getFavoriteIds());
        ids.add(ticketId);
        prefs.edit().putStringSet(KEY_FAVORITES, ids).apply();
    }

    public void removeFavorite(String ticketId) {
        Set<String> ids = new HashSet<>(getFavoriteIds());
        ids.remove(ticketId);
        prefs.edit().putStringSet(KEY_FAVORITES, ids).apply();
    }

    public void toggleFavorite(String ticketId) {
        if (isFavorite(ticketId)) {
            removeFavorite(ticketId);
        } else {
            addFavorite(ticketId);
        }
    }

    public Set<String> getFavoriteIds() {
        return prefs.getStringSet(KEY_FAVORITES, new HashSet<>());
    }
}