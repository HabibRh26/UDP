package org.example.repository;


import org.example.config.DbConnector;
import org.example.entities.AddressEntity;
import org.example.entities.ParentEntity;

import java.util.List;
import java.util.Scanner;

public class ParentRepository extends DbConnector {

    public ParentEntity save(ParentEntity parent) {
        try {
            super.getEm().persist(parent);
            super.getEm().getTransaction().commit();
            return parent;
        } finally {
            super.getEm().clear();
        }
    }

    public List<ParentEntity> getAllParents() {
        try {
            String queryString = "FROM ParentEntity"; // HQL to select all records
            return super.getEm().createQuery(queryString, ParentEntity.class).getResultList();
        } finally {
            super.getEm().clear();
        }
    }


    public ParentEntity findById(Integer id) {
        try {
            return super.getEm().find(ParentEntity.class, id);
        } finally {
            super.getEm().clear();
        }
    }

    public void deleteByIdParent(Integer id) {
        try {
            ParentEntity parent = super.getEm().find(ParentEntity.class, id);
            if (parent != null) {
                super.getEm().remove(parent);
                super.getEm().getTransaction().commit();
                System.out.println("Delete operation successful");
            } else {
                System.out.println("ParentEntity with ID " + id + " does not exist.");
            }
        } finally {
            super.getEm().clear();
        }
    }

    public ParentEntity updateParent(Integer updateId) {

        try {
            ParentEntity existingParent = super.getEm().find(ParentEntity.class, updateId);
            if (existingParent != null) {
                existingParent = loadUpdatedData(existingParent);
                super.getEm().merge(existingParent); // Merge changes to the existing entity
                super.getEm().getTransaction().commit();
                System.out.println("Update operation successful");
                return existingParent;
            } else {
                System.out.println("ParentEntity with ID " + updateId + " does not exist.");
                return null;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            super.getEm().clear();
        }
    }

    private ParentEntity loadUpdatedData(ParentEntity existingParent) {
        System.out.println("Enter the firstName: ");
        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine();
        System.out.println("Enter the lastName: ");
        String lastName = sc.nextLine();
        System.out.println("For Address- " + "\n" +
                "Enter the street: ");
        String street = sc.nextLine();
        System.out.println("Enter the city: ");
        String city = sc.nextLine();
        System.out.println("Enter the state: ");
        String state = sc.nextLine();
        System.out.println("Enter the zip: ");
        String zip = sc.next();

        existingParent.setFirstName(firstName);
        existingParent.setLastName(lastName);

        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setCity(city);
        addressEntity.setState(state);
        addressEntity.setStreet(street);
        addressEntity.setZip(zip);
        existingParent.setAddressEntity(addressEntity);

        return existingParent;
    }
}
