package com.watersolution.inventory.component.management.product.disposal.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DisposalList extends ResponseDefault {

    private List<Disposal> disposalList;

    public DisposalList(List<Disposal> disposalList) {
        this.disposalList = disposalList;
    }
}
