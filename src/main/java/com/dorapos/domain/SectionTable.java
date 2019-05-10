package com.dorapos.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import org.springframework.data.elasticsearch.annotations.FieldType;
import java.io.Serializable;
import java.util.Objects;

/**
 * A SectionTable.
 */
@Entity
@Table(name = "section_table")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@org.springframework.data.elasticsearch.annotations.Document(indexName = "sectiontable")
public class SectionTable implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    @org.springframework.data.elasticsearch.annotations.Field(type = FieldType.Keyword)
    private Long id;

    @Column(name = "table_number")
    private Integer tableNumber;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JsonIgnoreProperties("sectionTables")
    private ShopSection shopSections;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTableNumber() {
        return tableNumber;
    }

    public SectionTable tableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
        return this;
    }

    public void setTableNumber(Integer tableNumber) {
        this.tableNumber = tableNumber;
    }

    public String getDescription() {
        return description;
    }

    public SectionTable description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ShopSection getShopSections() {
        return shopSections;
    }

    public SectionTable shopSections(ShopSection shopSection) {
        this.shopSections = shopSection;
        return this;
    }

    public void setShopSections(ShopSection shopSection) {
        this.shopSections = shopSection;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SectionTable)) {
            return false;
        }
        return id != null && id.equals(((SectionTable) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SectionTable{" +
            "id=" + getId() +
            ", tableNumber=" + getTableNumber() +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
