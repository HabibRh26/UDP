package org.example.service;


import org.example.Utils.Validator;
import org.example.entities.AddressEntity;
import org.example.entities.ChildEntity;
import org.example.entities.ParentEntity;
import org.example.repository.ParentRepository;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ParentService {
    public void deleteParent() {
        ParentRepository parentRepository = new ParentRepository();
        int dataSize = parentRepository.getAllParents().size();
        if (dataSize == 0)
        {
            System.out.println("-----------------------------------------------------------------------------");
            System.err.println("No data available. Please insert some data.");
        }

        else {
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Enter the id of which you want to delete");
            try {
                Scanner sc = new Scanner(System.in);
                Integer id = sc.nextInt();
                ParentEntity parentEntity = parentRepository.findById(id);
                if (Objects.isNull(parentEntity))
                    System.err.println("Invalid Id!");
                else {
                    parentRepository.deleteByIdParent(id);
                    System.out.println("Here is table data -");
                    displayCurrentTable();
                }
            } catch (Exception e) {
                System.err.println("Invalid Id!");
            }

        }

    }

    public void updateParent() {
        ParentRepository parentRepository = new ParentRepository();
        int dataSize = parentRepository.getAllParents().size();
        if (dataSize == 0)
            System.err.println("No data available. Please insert some data.");
        else {
            parentRepository = new ParentRepository();
            displayCurrentTable();
            System.out.println("-----------------------------------------------------------------------------");
            System.out.println("Enter the id of which you want to update");
            try {
                Scanner sc = new Scanner(System.in);
                Integer id = sc.nextInt();
                ParentEntity parentEntity = parentRepository.findById(id);
                if (Objects.isNull(parentEntity))
                    System.err.println("Invalid Id!");
                parentRepository.updateParent(id);
            } catch (Exception e) {
                System.err.println("Invalid Id!");
            }
            System.out.println("Here is the table data -");
            displayCurrentTable();
        }

    }

    public void createParent() {
        System.out.println("-----------------------------------------------------------------------------");
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
        System.out.println("Enter the zip Code (In Number): ");
        String zip = sc.nextLine();

        Validator validator = new Validator();
        if (!validator.validateName(firstName) || !validator.validateName(lastName) || !validator.validateState(state) ||
                !validator.validateCity(city) || !validator.validateStreet(street) || !validator.validateZip(zip))
            System.err.println("Invalid input data! Try again with valid input type.");
        else {
            ParentRepository parentRepository = new ParentRepository();
            ParentEntity parentEntity = new ParentEntity();
            parentEntity.setFirstName(firstName);
            parentEntity.setLastName(lastName);

            AddressEntity addressEntity = new AddressEntity();
            addressEntity.setCity(city);
            addressEntity.setState(state);
            addressEntity.setStreet(street);
            addressEntity.setZip(zip);
            parentEntity.setAddressEntity(addressEntity);
            parentEntity.getChildren().add(addChildren(parentEntity));

            parentRepository.save(parentEntity);
            System.out.println("Saved successfully");
            System.out.println("Here is the table data- ");

            displayCurrentTable();

        }

    }

    public void displayCurrentTable() {
        ParentRepository parentRepository = new ParentRepository();
        int dataSize = parentRepository.getAllParents().size();
        if (dataSize == 0) {
            System.out.println("-----------------------------------------------------------------------------");
            System.err.println("No data available. Please insert some data.");
        }

        else {
            List<ParentEntity> parents = parentRepository.getAllParents();
            for (ParentEntity parent : parents) {
                System.out.println("-----------------------------------------------------------------------------");
                System.out.println("Parent ID: " + parent.getId());
                System.out.println("First Name: " + parent.getFirstName());
                System.out.println("Last Name: " + parent.getLastName());
                System.out.println("Street: " + parent.getAddressEntity().getStreet());
                System.out.println("City: " + parent.getAddressEntity().getCity());
                System.out.println("State: " + parent.getAddressEntity().getState());
                System.out.println("zip: " + parent.getAddressEntity().getZip());
                System.out.println("-------------------------------------");
                for (ChildEntity child : parent.getChildren()) {
                    System.out.println("Child's id :" + child.getId());
                    System.out.println("child's firstName :" + child.getFirstName());
                    System.out.println("child's lastName :" + child.getLastName());
                }
            }
        }

    }

    private ChildEntity addChildren(ParentEntity parentEntity) {
        System.out.println("Enter the firstName of the child: ");
        Scanner sc = new Scanner(System.in);
        String firstName = sc.nextLine();
        System.out.println("Enter the lastName of the child: ");
        String lastName = sc.nextLine();

        ChildEntity childEntity = new ChildEntity();
        childEntity.setFirstName(firstName);
        childEntity.setLastName(lastName);
        childEntity.setParent(parentEntity);
        return childEntity;
    }

}
