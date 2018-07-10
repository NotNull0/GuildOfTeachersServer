package com.mpls.web2.dto;


import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

@Dto
public class SpecializationFullDto extends SpecializationShortDto<SpecializationFullDto> {

    private List<UserShortDto> users;

    public List<UserShortDto> getUsers() {
        return users;
    }

    public SpecializationFullDto setUsers(List<UserShortDto> users) {
        this.users = users;
        return this;
    }

    @Override
    public String toString() {
        return "SpecializationFullDto{" +
                "users=" + users +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
