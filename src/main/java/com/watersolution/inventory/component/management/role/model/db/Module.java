package com.watersolution.inventory.component.management.role.model.db;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "module")
@EqualsAndHashCode(callSuper = false)
public class Module extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "module_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "module_seq", name = "module_seq")
    private long id;

    @Column(name = "permissionCode")
    private String permissionCode;

    @Column(name = "permissionDescription")
    private String permissionDescription;

    @Column(name = "getAll")
    private String getAll;

    @Column(name = "getBasic")
    private String getBasic;

    @Column(name = "added")
    private String add;

    @Column(name = "updated")
    private String update;

    @Column(name = "deleted")
    private String delete;

    @JsonManagedReference(value = "module")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "module")
    private List<Privilege> privileges;

    public Module() {
    }
}
