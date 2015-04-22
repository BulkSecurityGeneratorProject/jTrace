package com.spring.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * A Itemmaster.
 */
@Entity
@Table(name = "T_ITEMMASTER")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Itemmaster implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "item_code")
    private String itemCode;

    @Column(name = "description")
    private String description;

    @Column(name = "attributes")
    private String attributes;

    @ManyToOne
    private Itemcat itemcat;

    @ManyToOne
    private Itemsubcat itemsubcat;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAttributes() {
        return attributes;
    }

    public void setAttributes(String attributes) {
        this.attributes = attributes;
    }

    public Itemcat getItemcat() {
        return itemcat;
    }

    public void setItemcat(Itemcat itemcat) {
        this.itemcat = itemcat;
    }

    public Itemsubcat getItemsubcat() {
        return itemsubcat;
    }

    public void setItemsubcat(Itemsubcat itemsubcat) {
        this.itemsubcat = itemsubcat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Itemmaster itemmaster = (Itemmaster) o;

        if (id != null ? !id.equals(itemmaster.id) : itemmaster.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Itemmaster{" +
                "id=" + id +
                ", itemCode='" + itemCode + "'" +
                ", description='" + description + "'" +
                ", attributes='" + attributes + "'" +
                '}';
    }
}
