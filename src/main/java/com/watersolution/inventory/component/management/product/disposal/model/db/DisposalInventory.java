package com.watersolution.inventory.component.management.product.disposal.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "disposalinventory")
@EqualsAndHashCode(callSuper = false)
public class DisposalInventory extends Auditable {

    @EmbeddedId
    private DisposalInventoryId disposalInventoryId;

    @JsonBackReference(value = "disposal")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("disposalId")
    @JoinColumn(name = "disposal_id")
    private Disposal disposal;

    @JsonBackReference(value = "disposalInventory")
    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("inventoryId")
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @Column(name = "qty")
    private Integer qty;

    public DisposalInventory() {
    }
}
