package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

@Dto
public class TeachingProgramContainerShortDto<T extends TeachingProgramContainerShortDto> {

    protected Long id;
    protected String name;
    protected Boolean available = true;

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

    public Boolean getAvailable() {
        return available;
    }

    public T setAvailable(Boolean available) {
        this.available = available;
        return (T) this;
    }

    @Override
    public String toString() {
        return "TeachingProgramContainerShortDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
