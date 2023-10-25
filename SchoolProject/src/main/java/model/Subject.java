package model;

import javax.persistence.*;

@Entity
@Table(name = "subjects")

public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String subject;
    public Subject(){}

    public Subject( String subject) {
         this.subject = subject;
    }

    public int getId() {
        return id;
    }

    public void setId(int subjectId) {
        this.id = subjectId;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subjectName) {
        this.subject = subjectName;
    }

}
