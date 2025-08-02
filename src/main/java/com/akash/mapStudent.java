package com.akash;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


@Entity
public class mapStudent {

    @Id // primary key.
    private int rollNumber;
    private String name;
    private int age;
    private String gmail;
    private String mobileNumber;

    public int getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(int rollNumber) {
        this.rollNumber = rollNumber;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    @Override
    public String toString() {
        return "mapStudent{" +
                "rollNumber=" + rollNumber +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", gmail='" + gmail + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                '}';
    }
}
