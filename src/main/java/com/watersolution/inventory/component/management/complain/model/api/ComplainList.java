package com.watersolution.inventory.component.management.complain.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.complain.model.db.Complain;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ComplainList extends ResponseDefault {
    @Valid
    private List<Complain> complainList;

    public ComplainList(@Valid List<Complain> complainList) {
        this.complainList = complainList;
    }
}
