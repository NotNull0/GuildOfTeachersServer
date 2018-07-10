package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

@Dto
public class UserGeneralInfoDto<T extends UserGeneralInfoDto> {

    protected Long id;
    protected String name;
    protected String lastname;
    protected String surname;
    protected String email;
    protected String phone;
    protected String image;
    protected String facebookLink;

    public Long getId() {
        return id;
    }

    public T setId(Long id) {
        this.id = id;
        return (T) this;
    }

    public String getName() {
        return name;
    }

    public T setName(String name) {
        this.name = name;
        return (T) this;
    }

    public String getLastname() {
        return lastname;
    }

    public T setLastname(String lastname) {
        this.lastname = lastname;
        return (T) this;
    }

    public String getSurname() {
        return surname;
    }

    public T setSurname(String surname) {
        this.surname = surname;
        return (T) this;
    }

    public String getEmail() {
        return email;
    }

    public T setEmail(String email) {
        this.email = email;
        return (T) this;
    }

    public String getPhone() {
        return phone;
    }

    public T setPhone(String phone) {
        this.phone = phone;
        return (T) this;
    }

    public String getImage() {
        return image;
    }

    public T setImage(String image) {
        this.image = image;
        return (T) this;
    }

    public String getFacebookLink() {
        return facebookLink;
    }

    public T setFacebookLink(String facebookLink) {
        this.facebookLink = facebookLink;
        return (T) this;
    }

    @Override
    public String toString() {
        return "UserGeneralInfoDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
