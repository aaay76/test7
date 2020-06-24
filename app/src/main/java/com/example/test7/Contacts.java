package com.example.test7;

public class Contacts {
    public String name;
    public String number;
    public String sex;

    public Contacts(String name,String number,String sex){
        super();
        this.name=name;
        this.number=number;
        this.sex=sex;
    }

    public String getName() {
        return name;
    }

    public String getNumber() {
        return number;
    }

    public String getSex() {
        return sex;
    }
}
