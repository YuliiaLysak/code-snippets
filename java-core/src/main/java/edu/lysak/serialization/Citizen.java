package edu.lysak.serialization;

import java.io.Serializable;

public class Citizen implements Serializable {
    private static final long serialVersionUID = 1L;

    private String name;
    private Address address;
    private transient String passport;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getPassport() {
        return passport;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    @Override
    public String toString() {
        return "Citizen{" +
                "name='" + name + '\'' +
                ", passport='" + passport + '\'' +
                ", address=" + address +
                '}';
    }
}
