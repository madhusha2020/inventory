package com.watersolution.inventory.component.management.product.disposal.model.api;

import com.watersolution.inventory.component.common.exception.ResponseDefault;
import com.watersolution.inventory.component.management.product.disposal.model.db.Disposal;
import com.watersolution.inventory.component.management.product.disposal.model.db.DisposalInventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.Valid;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
public class DisposalInventoryList extends ResponseDefault {
    @Valid
    private Disposal disposal;
    @Valid
    private List<DisposalInventory> disposalInventoryList;

    public DisposalInventoryList(@Valid Disposal disposal, @Valid List<DisposalInventory> disposalInventoryList) {
        this.disposal = disposal;
        this.disposalInventoryList = disposalInventoryList;
    }
}
