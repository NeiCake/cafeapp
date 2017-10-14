package com.neicake.cafeapp.domain;

import javax.persistence.*;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private boolean active;
    @Column
    private int percentOffValue;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public int getPercentOffValue() {
        return percentOffValue;
    }

    public void setPercentOffValue(int percentOffValue) {
        this.percentOffValue = percentOffValue;
    }
}
