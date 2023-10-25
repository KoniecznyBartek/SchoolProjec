package model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String fname;
    private String lname;
    private int age;
    private String mail;


    public Person() {

    }

    public Person(int id,String fname, String lname, int age, String mail) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.age = age;
        this.mail = mail;
    }
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "personId")
    private List<Subject> subject;

    public List<Subject> getSubject() {
        return subject;
    }

    public void setSubject(List<Subject> subject) {
        this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    @Override
    public String toString() {
        return "Person{" +
                "first_name='" + fname + '\'' +
                ", last_name='" + lname + '\'' +
                ", age=" + age +
                ", mail='" + mail + '\'' +
                '}';
    }
}
