package com.watersolution.inventory.component.common.model.api;

import lombok.Data;

@Data
public class PageDetails {
    private SearchFilter searchFilter;
    private int offset;
    private int limit;

    public PageDetails(SearchFilter searchFilter, int offset, int limit) {
        this.searchFilter = searchFilter;
        this.offset = offset;
        this.limit = limit;
    }
}
