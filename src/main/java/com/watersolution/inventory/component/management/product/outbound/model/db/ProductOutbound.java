package com.watersolution.inventory.component.management.product.outbound.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "productoutbound")
@EqualsAndHashCode(callSuper = false)
public class ProductOutbound extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_outbound_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "product_outbound_seq", name = "product_outbound_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDateTime date;

    @ToString.Exclude
    @JsonManagedReference(value = "productOutbound")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "productOutbound", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<ProductOutboundItem> productOutboundItems;

    @Transient
    private Integer itemCount;

    public ProductOutbound() {

    }
}
