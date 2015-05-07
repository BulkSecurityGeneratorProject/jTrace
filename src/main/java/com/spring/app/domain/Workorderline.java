package com.spring.app.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Workorderline.
 */
@Entity
@Table(name = "WORKORDERLINE")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Workorderline implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(name = "bom_child_item", nullable = false)
    private String bomChildItem;

    @Column(name = "attrition")
    private String attrition;

    @Column(name = "requ_qty")
    private Integer requQty;

    @Column(name = "issued_qty")
    private Integer issuedQty;

    @Column(name = "is_cust_supplied")
    private Boolean isCustSupplied;

    @Size(min = 5)
    @Column(name = "remark")
    private String remark;

    @ManyToOne
    private Itemctn itemctn;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBomChildItem() {
        return bomChildItem;
    }

    public void setBomChildItem(String bomChildItem) {
        this.bomChildItem = bomChildItem;
    }

    public String getAttrition() {
        return attrition;
    }

    public void setAttrition(String attrition) {
        this.attrition = attrition;
    }

    public Integer getRequQty() {
        return requQty;
    }

    public void setRequQty(Integer requQty) {
        this.requQty = requQty;
    }

    public Integer getIssuedQty() {
        return issuedQty;
    }

    public void setIssuedQty(Integer issuedQty) {
        this.issuedQty = issuedQty;
    }

    public Boolean getIsCustSupplied() {
        return isCustSupplied;
    }

    public void setIsCustSupplied(Boolean isCustSupplied) {
        this.isCustSupplied = isCustSupplied;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Itemctn getItemctn() {
        return itemctn;
    }

    public void setItemctn(Itemctn itemctn) {
        this.itemctn = itemctn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Workorderline workorderline = (Workorderline) o;

        if ( ! Objects.equals(id, workorderline.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Workorderline{" +
                "id=" + id +
                ", bomChildItem='" + bomChildItem + "'" +
                ", attrition='" + attrition + "'" +
                ", requQty='" + requQty + "'" +
                ", issuedQty='" + issuedQty + "'" +
                ", isCustSupplied='" + isCustSupplied + "'" +
                ", remark='" + remark + "'" +
                '}';
    }
}
