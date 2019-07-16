package com.ukuya.mspc.api.model;

import com.google.gson.annotations.SerializedName;
import com.ukuya.mspc.model.Event;

import java.util.ArrayList;
import java.util.List;


public class EventResponse {
    @SerializedName("events")
    private List<Event> events;
    @SerializedName("_links")
    private Links links;
    @SerializedName("_meta")
    private Meta meta;

    public EventResponse() {
        this.events = new ArrayList<>();
        this.links = new Links();
        this.meta = new Meta();
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public Links getLinks() {
        return links;
    }

    public void setLinks(Links links) {
        this.links = links;
    }

    public Meta getMeta() {
        return meta;
    }

    public void setMeta(Meta meta) {
        this.meta = meta;
    }
}
