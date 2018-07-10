package com.mpls.web2.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.model.utils.DateSerializer;

import java.sql.Timestamp;

@Dto
public class ChatUserWrapperDto {

    private Long id;
    private Timestamp datetime;
    private Boolean available;
    private UserGeneralInfoDto user;

    public Long getId() {
        return id;
    }

    public ChatUserWrapperDto setId(Long id) {
        this.id = id;
        return this;
    }

    //    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    @JsonSerialize(using = DateSerializer.class)
    public Timestamp getDatetime() {
        return datetime;
    }

    public ChatUserWrapperDto setDatetime(Timestamp datetime) {
        this.datetime = datetime;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public ChatUserWrapperDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    public UserGeneralInfoDto getUser() {
        return user;
    }

    public ChatUserWrapperDto setUser(UserGeneralInfoDto user) {
        this.user = user;
        return this;
    }

    @Override
    public String toString() {
        return "ChatUserWrapperDto{" +
                "id=" + id +
                ", datetime=" + datetime +
                ", available=" + available +
                ", user=" + user +
                '}';
    }
}
