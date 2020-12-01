package com.watersolution.inventory.component.management.notification.model.db;

import com.watersolution.inventory.component.common.model.db.Auditable;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "notification")
@EqualsAndHashCode(callSuper = false)
public class Notification extends Auditable {

    @Id
    @Column(name = "id", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_order_seq")
    @SequenceGenerator(initialValue = 1, sequenceName = "customer_order_seq", name = "customer_order_seq")
    private long id;

    @Column(name = "username")
    private String userName;

    @Column(name = "dosend")
    private LocalDate dosend;

    @Column(name = "dodelivered")
    private LocalDate dodelivered;

    @Column(name = "doreceived")
    private LocalDate doreceived;

    @Column(name = "type")
    private String type;

    @Lob
    private String message;

    @Lob
    private String link;

    public Notification() {
    }
}
