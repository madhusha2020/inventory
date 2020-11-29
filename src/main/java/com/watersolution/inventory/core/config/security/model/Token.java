package com.watersolution.inventory.core.config.security.model;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
@Entity
@Table(name = "token")
@EqualsAndHashCode(callSuper = false)
public class Token extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "token_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "token_seq", name = "token_seq")
    private long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "ipaddress")
    private String ipAddress;

    @Lob
    private String token;
}
