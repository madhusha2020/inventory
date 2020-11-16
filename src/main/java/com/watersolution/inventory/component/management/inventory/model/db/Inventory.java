package com.watersolution.inventory.component.management.inventory.model.db;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.common.validator.annotations.FutureDateValidateConstraint;
import com.watersolution.inventory.component.common.validator.annotations.QuantityValidateConstraint;
import com.watersolution.inventory.component.entity.item.model.db.Item;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "inventory")
@EqualsAndHashCode(callSuper = false)
public class Inventory extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "inventory_seq", name = "inventory_seq")
    private long id;

    @NotBlank(message = "Item Code must not be blank")
    @Column(name = "code")
    private String code;

    @Column(name = "description")
    private String description;

    @QuantityValidateConstraint(message = "Invalid quantity")
    @Column(name = "initqty")
    private Integer initqty;

    @QuantityValidateConstraint(message = "Invalid quantity")
    @Column(name = "qty")
    private Integer qty;

    @FutureDateValidateConstraint(message = "Invalid expiry date")
    @Column(name = "doexpire")
    private LocalDate doexpire;

    @JsonBackReference(value = "inventory_item")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;
}
