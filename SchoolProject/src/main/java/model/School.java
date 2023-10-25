package model;

import javax.persistence.*;


@Entity
@Table(name = "schools")
public class School {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int schoolId;
    private String name;

    public School(int schoolId, String name) {
        this.schoolId = schoolId;
        this.name = name;
    }

    public School() {

    }

    public int getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(int id) {
        this.schoolId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
