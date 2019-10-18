package dk.bringlarsen.springboot.multipledb.user.model;

import javax.persistence.*;

@Entity
@Table
public class User {

    @Id
    private int id;

    private String name;

    @Column(unique = true, nullable = false)
    private String email;

    private int age;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
