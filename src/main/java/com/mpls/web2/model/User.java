package com.mpls.web2.model;

import com.mpls.web2.model.enums.Incumbency;
import com.mpls.web2.model.enums.UserRole;
import org.hibernate.annotations.Cascade;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Entity
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String lastname;
    private String surname;
    private String email;
    private String phone;
    private String password;
    private String uuid;
    private String facebookLink;
    private Incumbency incumbency;
    private UserRole role;
    private Boolean available = true;
    private Boolean perevireno;
    @Column(columnDefinition = "LONGTEXT")
    private String information;
    @Column(columnDefinition = "TEXT(1000)")
    private String image;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH, orphanRemoval = true)
    private PlaceOfWork placeOfWork;
    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<File> files;
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    @JoinTable(name = "user_specialization",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "specialization_id"))
    private List<Specialization> specializations;


    public User() {
    }

    public Boolean getPerevireno() {
        return perevireno;
    }

    public User setPerevireno(Boolean perevireno) {
        this.perevireno = perevireno;
        return this;
    }

    public Long getId() {
        return id;
    }

    public User setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getLastname() {
        return lastname;
    }

    public User setLastname(String lastname) {
        this.lastname = lastname;
        return this;
    }

    public String getSurname() {
        return surname;
    }

    public User setSurname(String surname) {
        this.surname = surname;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public User setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getInformation() {
        return information;
    }

    public User setInformation(String information) {
        this.information = information;
        return this;
    }

//    public List<Questionnaire> getQuestionnaires() {
//        return questionnaires;
//    }
//
//    public User setQuestionnaires(List<Questionnaire> questionnaires) {
//        this.questionnaires = questionnaires;
//        return this;
//    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> grantedAuthorities = new ArrayList<>();
        grantedAuthorities.add(new SimpleGrantedAuthority(role.name().toUpperCase()));
        return grantedAuthorities;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return available;
    }

    @Override
    public boolean isAccountNonLocked() {
        return available;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return available;
    }

    @Override
    public boolean isEnabled() {
        return available;
    }

    public String getUuid() {
        return uuid;
    }

    public User setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public String getImage() {
        return image;
    }

    public User setImage(String image) {
        this.image = image;
        return this;
    }

    //    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public PlaceOfWork getPlaceOfWork() {
        return placeOfWork;
    }

    public User setPlaceOfWork(PlaceOfWork placeOfWork) {
        this.placeOfWork = placeOfWork;
        return this;
    }

    //    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    public List<Specialization> getSpecializations() {
        return specializations;
    }

    public User setSpecializations(List<Specialization> specializations) {
        this.specializations = specializations;
        return this;
    }

    public Incumbency getIncumbency() {
        return incumbency;
    }

    public User setIncumbency(Incumbency incumbency) {
        this.incumbency = incumbency;
        return this;
    }

    public UserRole getRole() {
        return role;
    }

    public User setRole(UserRole role) {
        this.role = role;
        return this;
    }

    public List<File> getFiles() {
        return files;
    }

    public User setFiles(List<File> files) {
        this.files = files;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public User setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public User setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", information='" + information + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", image='" + image + '\'' +
                ", placeOfWork=" + (placeOfWork == null ? "null" : placeOfWork.getName()) +
                ", specializations=" + specializations.stream().map(Specialization::getName).collect(toList()) +
                ", incumbency=" + incumbency +
                ", role=" + role +
                ", files=" + files.stream().map(File::getName).collect(toList()) +
                ", available=" + available +
                '}';
    }
}
