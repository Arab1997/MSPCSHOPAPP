package com.ukuya.mspc.api.model;

import com.google.gson.annotations.SerializedName;

public class Meta {
    @SerializedName("totalCount")
    private Integer totalCount;
    @SerializedName("pageCount")
    private Integer pageCount;
    @SerializedName("currentPage")
    private Integer currentPage;
    @SerializedName("perPage")
    private Integer perPage;

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public void setPageCount(Integer pageCount) {
        this.pageCount = pageCount;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPerPage() {
        return perPage;
    }

    public void setPerPage(Integer perPage) {
        this.perPage = perPage;
    }
}
