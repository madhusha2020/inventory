package com.watersolution.inventory.component.management.product.inbound.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "productinbound")
@EqualsAndHashCode(callSuper = false)
public class ProductInbound extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_inbound_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "product_inbound_seq", name = "product_inbound_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @ToString.Exclude
    @JsonManagedReference(value = "productInbound")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productInbound", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductInboundItem> productInboundItems;

    @Transient
    private Integer itemCount;

    public ProductInbound() {

    }
}
