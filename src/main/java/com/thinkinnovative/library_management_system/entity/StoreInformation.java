package com.thinkinnovative.library_management_system.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class StoreInformation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer Id;
    private String name;
    private String accountNumber;
    private Integer password;
    private Integer balance;

    public StoreInformation() {

    }
    public StoreInformation( String name, String accountNumber, Integer password, Integer balance) {

        this.name = name;
        this.accountNumber = accountNumber;
        this.password = password;
        this.balance = balance;
    }
public String getAccountNumber() {
    return accountNumber;
}

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPassword() {
        return password;
    }

    public void setPassword(Integer password) {
        this.password = password;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }
    @Override
    public String toString() {
        return "StoreInformation{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", password=" + password +
                ", balance=" + balance +
                '}';
    }

}
