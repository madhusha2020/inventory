package com.watersolution.inventory.component.common.model.api;

import lombok.Data;

import javax.validation.Valid;
import java.util.List;

@Data
public class SearchFilter {
    private String userId;
    private String name;
    private String type;
    @Valid
    private List<Integer> statusList;
}
