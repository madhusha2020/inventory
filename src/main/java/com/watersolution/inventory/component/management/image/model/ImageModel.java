package com.watersolution.inventory.component.management.image.model;


import com.watersolution.inventory.component.common.exception.ResponseDefault;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;

@Data
//@Entity
//@Table(name = "image")
@EqualsAndHashCode(callSuper = false)
public class ImageModel extends ResponseDefault {

//    @Id
//    @Column(name = "id", nullable = false, unique = true)
//    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "img_seq")
//    @SequenceGenerator(initialValue = 1, sequenceName = "img_seq", name = "img_seq")
//    private long id;

//    @Column(name = "name")
    private String name;

//    @Column(name = "type")
    private String type;

//    @Column(name = "photo", length = 1000)
    private byte[] photo;

    public ImageModel() {
    }

    public ImageModel(byte[] photo) {
        this.photo = photo;
    }


    public ImageModel(String name, String type, byte[] photo) {
        this.name = name;
        this.type = type;
        this.photo = photo;
    }
}
