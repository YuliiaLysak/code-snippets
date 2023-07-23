package edu.lysak.java8Training;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class TestOptional {
    static List<Person> persons = Arrays.asList(
            new Person("One", null),
            new Person("Two", new Contact(new Address(null))),
            new Person("Three", new Contact(new Address("Ukraine")))
    );

    public static void main(String[] args) {
        for (Person person : persons) {
            System.out.println(getContactCountry(person));
        }
        for (Person person : persons) {
            System.out.println(getContactCountryByStreams(person));
        }
        for (Person person : persons) {
            System.out.println(getContactCountryByStreams2(person));
        }
    }

    private static String getContactCountry(Person person) {
        String contactCountry = "UNKNOWN";
        if (person != null) {
            Contact contact = person.getContacts();
            if (contact != null) {
                Address address = contact.getAddress();
                if (address != null) {
                    String country = address.getCountry();
                    if (country != null) {
                        contactCountry = country;
                    }
                }
            }
        }
        return contactCountry;
    }

    private static String getContactCountryByStreams(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getContactAsOptional)
                .flatMap(Contact::getAddressAsOptional)
                .map(Address::getCountry)
                .orElse("UNKNOWN");
    }

    private static String getContactCountryByStreams2(Person person) {
        return Optional.ofNullable(person)
                .flatMap(Person::getContactAsOptional)
                .map(Contact::getAddress)
                .map(Address::getCountry)
                .orElse("UNKNOWN");
    }
}

class Person {
    private String name;
    private Contact contact;

    public Person() {
        super();
    }

    public Person(String name, Contact contact) {
        super();
        this.name = name;
        this.contact = contact;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContacts() {
        return contact;
    }

    public void setContacts(Contact contact) {
        this.contact = contact;
    }

    public Optional<Contact> getContactAsOptional() {
        return Optional.ofNullable(contact);
    }

}

class Contact {
    private Address address;
    private String email;

    public Contact() {
        super();
    }

    public Contact(Address address) {
        super();
        this.address = address;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Optional<Address> getAddressAsOptional() {
        return Optional.ofNullable(address);
    }

}

class Address {
    private String country;
    private String zip;

    public Address() {
        super();
    }

    public Address(String country) {
        super();
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
