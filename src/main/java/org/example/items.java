package org.example;

import javax.persistence.*;

@Entity
@Table(name = "items")
public class items {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "items_ID")
    private Long itemsId;


    @Version
    @Column(name = "version")
    private Long version;

    @Column(name = "val")
    private int val;


    public items() {
    }

    public Long getItemsId() {
        return itemsId;
    }

    public void setItemsId(Long cruiseId) {
        this.itemsId = cruiseId;
    }


    public Long getVersion() {
        return version;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

}

