package com.kgc.house.util;

public class Page {
    private Integer page=1;
    private  Integer rows=2;

    public Page(Integer page, Integer rows) {
        this.page = page;
        this.rows = rows;
    }

    public Page() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}
