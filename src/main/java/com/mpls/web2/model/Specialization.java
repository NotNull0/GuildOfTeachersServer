package com.mpls.web2.model;

import javax.persistence.*;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class Specialization {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private Boolean available = true;
    @ManyToMany
    @JoinTable(name = "user_specialization",
            joinColumns = @JoinColumn(name = "specialization_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    private List<User> users;

    public Specialization() {
    }

    public Long getId() {
        return id;
    }

    public Specialization setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Specialization setName(String name) {
        this.name = name;
        return this;
    }

    public List<User> getUsers() {
        return users;
    }

    public Specialization setUsers(List<User> users) {
        this.users = users;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public Specialization setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "Specialization{" +
                "id=" + id +
                ", name=" + (name == null ? "null" : name) +
                ", users=" + (users == null ? "null" : users.stream().map(User::getId).collect(toList())) +
                ", available=" + available +
                '}';
    }
}
