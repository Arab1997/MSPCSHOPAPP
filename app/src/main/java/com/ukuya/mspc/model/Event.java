package com.ukuya.mspc.model;

import com.google.gson.annotations.SerializedName;
import com.ukuya.mspc.di.module.BaseModel;
import com.ukuya.mspc.di.module.CreatorModel;

public class Event {
    @SerializedName("id")
    private Integer id;
    @SerializedName("name")
    private String name;
    @SerializedName("short_description")
    private String shortDescription;
    @SerializedName("slug")
    private String slug;
    @SerializedName("image")
    private String image;
    @SerializedName("created_at")
    private Long createAt;
    @SerializedName("start_time")
    private Long startTime;
    @SerializedName("end_time")
    private Long endTime;
    @SerializedName("city")
    private BaseModel city;
    @SerializedName("creator")
    private CreatorModel creatorModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Long getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Long createAt) {
        this.createAt = createAt;
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getEndTime() {
        return endTime;
    }

    public void setEndTime(Long endTime) {
        this.endTime = endTime;
    }

    public BaseModel getCity() {
        return city;
    }

    public void setCity(BaseModel city) {
        this.city = city;
    }

    public CreatorModel getCreatorModel() {
        return creatorModel;
    }

    public void setCreatorModel(CreatorModel creatorModel) {
        this.creatorModel = creatorModel;
    }

}
