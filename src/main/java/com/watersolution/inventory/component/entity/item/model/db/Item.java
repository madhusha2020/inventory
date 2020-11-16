package com.watersolution.inventory.component.entity.item.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@Entity
@Table(name = "item")
@EqualsAndHashCode(callSuper = false)
public class Item extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "item_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "item_seq", name = "item_seq")
    private long id;

    @NotBlank(message = "Item Code must not be blank")
    @Column(name = "code")
    private String code;

    @NotBlank(message = "Item Name must not be blank")
    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "photo",  columnDefinition = "LONGBLOB")
    private byte[] photo;

    @NotBlank(message = "Item danger level must not be blank")
    @Column(name = "dangerlevel")
    private String dangerlevel;

    @Column(name = "testperiod")
    private Integer testperiod;

    @Column(name = "lastprice")
    private Double lastprice;

    @Column(name = "sprice")
    private Double sprice;

    @Transient
    private String spriceValue;

    @Transient
    private String lastpriceValue;

    @JsonManagedReference(value = "item")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderItems> orderItems;
}
