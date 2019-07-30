package com.hexad.bakery.entities;

import com.hexad.bakery.models.Pack;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Table(name = "PRODUCT")
public class Product extends AbstractEntity {

    private String name;
    private String code;

    @OneToMany(mappedBy = "product")
    private Set<Pack> packs;

    public Product() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Set<Pack> getPacks() {
        return packs;
    }

    public void setPacks(Set<Pack> packs) {
        this.packs = packs;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(code, product.code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code);
    }
}
