package com.github.zcmee.excelutil.dtoes;

import java.util.Date;

public class B2B {
    private Integer id;
    private String name;
    private String address;
    private Date dateSigningContract;

    public B2B() {
        // default constructor
    }

    public B2B(Integer id, String name, String address, Date dateSigningContract) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.dateSigningContract = dateSigningContract;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getDateSigningContract() {
        return dateSigningContract;
    }

    public void setDateSigningContract(Date dateSigningContract) {
        this.dateSigningContract = dateSigningContract;
    }

    @Override
    public String toString() {
        return "B2B{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", dateSigningContract=" + dateSigningContract +
                '}';
    }
}
