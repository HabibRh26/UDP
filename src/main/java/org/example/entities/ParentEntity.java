package org.example.entities;

import jakarta.persistence.*;

import java.util.LinkedHashSet;
import java.util.Set;

@Table(name = "parent_table")
@Entity
public class ParentEntity {
    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    @OneToOne(cascade = CascadeType.ALL)
    private AddressEntity addressEntity;
    @OneToMany(mappedBy = "parent", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<ChildEntity> children = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public AddressEntity getAddressEntity() {
        return addressEntity;
    }

    public void setAddressEntity(AddressEntity addressEntity) {
        this.addressEntity = addressEntity;
    }

    public Set<ChildEntity> getChildren() {
        return children;
    }

    public void setChildren(Set<ChildEntity> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "ParentEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", addressEntity=" + this.addressEntity +
                ", children=" + children +
                '}' + '\'';
    }
}
