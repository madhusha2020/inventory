package com.watersolution.inventory.component.entity.employee.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.entity.employee.model.db.Designation;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DesignationList extends ResponseDefault {
    @Valid
    private List<Designation> designations;

    public DesignationList(@Valid List<Designation> designations) {
        this.designations = designations;
    }
}
