package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.PlaceOfWork;
import com.mpls.web2.model.Specialization;
import com.mpls.web2.model.enums.Incumbency;
import com.mpls.web2.model.enums.UserRole;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class UserShortDto<T extends UserShortDto> extends UserGeneralInfoDto<T> {

    protected Incumbency incumbency;
    protected UserRole role;
    protected Boolean available = true;
    protected PlaceOfWork placeOfWork;
    protected List<SpecializationShortDto> specializations;
    protected Boolean perevireno;

    public Incumbency getIncumbency() {
        return incumbency;
    }

    public T setIncumbency(Incumbency incumbency) {
        this.incumbency = incumbency;
        return (T) this;
    }

    public UserRole getRole() {
        return role;
    }

    public T setRole(UserRole role) {
        this.role = role;
        return (T) this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    public PlaceOfWork getPlaceOfWork() {
        return placeOfWork;
    }

    public T setPlaceOfWork(PlaceOfWork placeOfWork) {
        this.placeOfWork = placeOfWork;
        return (T) this;
    }

    public List<SpecializationShortDto> getSpecializations() {
        return specializations;
    }

    public T setSpecializations(List<SpecializationShortDto> specializations) {
        this.specializations = specializations;
        return (T) this;
    }

    public Boolean getPerevireno() {
        return perevireno;
    }

    public T setPerevireno(Boolean perevireno) {
        this.perevireno = perevireno;
        return (T) this;
    }

    @Override
    public String toString() {
        return "UserShortDto{" +
                "incumbency=" + incumbency +
                ", role=" + role +
                ", available=" + available +
                ", placeOfWork=" + placeOfWork.getName() +
                ", specializations=" + specializations.stream().map(SpecializationShortDto::getName).collect(toList()) +
                ", perevireno=" + perevireno +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", lastname='" + lastname + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
