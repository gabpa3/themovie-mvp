package com.gabcode.themovie_mvp.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseApi<T> {

    private Integer page;
    @SerializedName("total_results") private Integer totalResult;
    @SerializedName("total_pages") private Integer totalPages;
    private List<T> results;

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotalResult() {
        return totalResult;
    }

    public void setTotalResult(Integer totalResult) {
        this.totalResult = totalResult;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public List<T> getResults() {
        return results;
    }

    public void setResults(List<T> results) {
        this.results = results;
    }
}
