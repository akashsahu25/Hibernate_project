package com.akash;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import jakarta.persistence.Query;

import java.sql.SQLOutput;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Main {

    static void addRecord(SessionFactory factory, Scanner sc) {
        mapStudent s = new mapStudent();

        System.out.print("Enter Roll Number: ");
        s.setRollNumber(sc.nextInt());

        System.out.print("Enter Age: ");
        s.setAge(sc.nextInt());
        sc.nextLine(); // consume leftover newline

        System.out.print("Enter Name: ");
        s.setName(sc.nextLine());

        System.out.print("Enter Gmail: ");
        s.setGmail(sc.nextLine());

        System.out.print("Enter Mobile Number: ");
        s.setMobileNumber(sc.nextLine());

        Session session = factory.openSession();

        session.beginTransaction();
        session.persist(s);
        session.getTransaction().commit();
        session.close();
        System.out.println("✅ Record Added Successfully!");
    }

    static void deleteRecord(SessionFactory factory, Scanner sc) {
        System.out.println("Please enter the roll number of the record you want to delete:");
        int deleteRollnumber = sc.nextInt();
        Session session = factory.openSession();

        session.beginTransaction();

        mapStudent student = session.find(mapStudent.class, deleteRollnumber);

        if (student != null) {
            session.remove(student);
            session.getTransaction().commit();
            System.out.println("Record deleted successfully." + student.getName());
        } else {
            System.out.println("No record found with roll number: " + deleteRollnumber);
        }
        session.close();
    }

    static void updateRecord() {
        System.out.println("Update Record Function Called");
    }

    static void printRecord(SessionFactory factory) {
        Session session = factory.openSession();
        session.beginTransaction();

        // mapStudent ka data fetch karna
        List<mapStudent> list = session.createQuery("FROM mapStudent", mapStudent.class).list();

        // Agar data hai to print karo
        if (list.isEmpty()) {
            System.out.println("⚠ No records found in the database.");
        } else {
            System.out.println("---- Student Records ----");
            for (mapStudent s : list) {
                System.out.println("Roll No: " + s.getRollNumber() +
                        " | Name: " + s.getName() +
                        " | Age: " + s.getAge() +
                        " | Gmail: " + s.getGmail() +
                        " | Mobile: " + s.getMobileNumber());
            }
            session.getTransaction().commit();
            session.close();

        }
    }

        public static void main (String args[]){

            Configuration config = new Configuration();
            config.configure("hibernate.cfg.xml");
            config.addAnnotatedClass(mapStudent.class);
            SessionFactory factory = config.buildSessionFactory();

            int f = 0;
            Scanner sc = new Scanner(System.in); // Scanner declared once outside the loop

            while (f == 0) {
                System.out.println("Which operation do you want to perform?");
                System.out.println("1 - Add Record");
                System.out.println("2 - Delete Record");
                System.out.println("3 - Update Record");
                System.out.println("4 - Print Record");
                System.out.println("5 - Exit");

                int operation = sc.nextInt();

                switch (operation) {
                    case 1:
                        addRecord(factory, sc);
                        break;
                    case 2:
                        deleteRecord(factory, sc);
                        break;
                    case 3:
                        updateRecord();
                        break;
                    case 4:
                        printRecord(factory);
                        break;
                    case 5:
                        f = 1;
                        System.out.println("Exiting program.");
                        break;
                    default:
                        System.out.println("Wrong choice! Please select a correct option.");
                }

            }
            sc.close();
        }
    }
