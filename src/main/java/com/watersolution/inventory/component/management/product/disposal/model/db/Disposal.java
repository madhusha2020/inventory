package com.watersolution.inventory.component.management.product.disposal.model.db;

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
@Table(name = "disposal")
@EqualsAndHashCode(callSuper = false)
public class Disposal extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "disposal_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "disposal_seq", name = "disposal_seq")
    private long id;

    @Column(name = "code")
    private String code;

    @Lob
    private String description;

    @Column(name = "date")
    private LocalDate date;

    @Lob
    private String reason;

    @ToString.Exclude
    @JsonManagedReference(value = "disposal")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "disposal", fetch = FetchType.LAZY, orphanRemoval = true)
    private List<DisposalInventory> disposalInventoryList;
}
