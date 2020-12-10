package com.watersolution.inventory.component.entity.discount.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "discount")
@EqualsAndHashCode(callSuper = false)
public class Discount extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "discount_seq")
    @SequenceGenerator(initialValue = 2, sequenceName = "discount_seq", name = "discount_seq")
    private long id;

    @Column(name = "category")
    private String category;

    @Column(name = "type")
    private String type;

    @Column(name = "discount")
    private BigDecimal discount;

    public Discount() {
    }
}
