package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

@Dto
public class SpecializationShortDto<T extends SpecializationShortDto> {

    protected Long id;
    protected String name;
    protected Boolean available;

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
        return "SpecializationShortDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
