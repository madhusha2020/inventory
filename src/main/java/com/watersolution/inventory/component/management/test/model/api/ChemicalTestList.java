package com.watersolution.inventory.component.management.test.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.test.model.db.ChemicalTest;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class ChemicalTestList extends ResponseDefault {

    @Valid
    private List<ChemicalTest> chemicalTestList;

    public ChemicalTestList(@Valid List<ChemicalTest> chemicalTestList) {
        this.chemicalTestList = chemicalTestList;
    }
}
