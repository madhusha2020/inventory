package com.watersolution.inventory.component.entity.item.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import com.watersolution.inventory.component.management.grn.model.db.PurchaseItems;
import com.watersolution.inventory.component.management.inventory.model.db.Inventory;
import com.watersolution.inventory.component.management.order.model.db.OrderItems;
import com.watersolution.inventory.component.management.product.inbound.model.db.ProductInboundItem;
import com.watersolution.inventory.component.management.product.outbound.model.db.ProductOutboundItem;
import com.watersolution.inventory.component.management.purchase.model.db.PurchaseOrderItems;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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

    @Lob
    private String description;

    @Column(name = "photo", columnDefinition = "LONGBLOB")
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

    @Column(name = "rop")
    private Integer rop;

    @Column(name = "unit")
    private String unit;

    @Column(name = "weightvolume")
    private String weightvolume;

    @ToString.Exclude
    @JsonManagedReference(value = "inventory_item")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private Inventory inventory;

    @ToString.Exclude
    @JsonManagedReference(value = "item")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<OrderItems> orderItems;

    @ToString.Exclude
    @JsonManagedReference(value = "productOutboundItem")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<ProductOutboundItem> productOutboundItems;

    @ToString.Exclude
    @JsonManagedReference(value = "productInboundItem")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<ProductInboundItem> productInboundItems;

    @ToString.Exclude
    @JsonManagedReference(value = "purchaseorderitem")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<PurchaseOrderItems> purchaseOrderItems;

    @ToString.Exclude
    @JsonManagedReference(value = "purchaseitem")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "item")
    private List<PurchaseItems> purchaseItems;

    @Transient
    private String spriceValue;

    @Transient
    private String lastpriceValue;

    @Transient
    private Integer avalableQty;

    @Transient
    private Integer orderedQty;

    public Item() {
    }
}
