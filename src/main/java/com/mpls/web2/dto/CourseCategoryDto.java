package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

@Dto
public class CourseCategoryDto {

    private Long id;
    private String name;
    private Double count;
    private Boolean available;

    public Long getId() {
        return id;
    }

    public CourseCategoryDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public CourseCategoryDto setName(String name) {
        this.name = name;
        return this;
    }

    public Double getCount() {
        return count;
    }

    public CourseCategoryDto setCount(Double count) {
        this.count = count;
        return this;
    }

    public Boolean getAvailable() {
        return available;
    }

    public CourseCategoryDto setAvailable(Boolean available) {
        this.available = available;
        return this;
    }

    @Override
    public String toString() {
        return "CourseCategoryDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", available=" + available +
                '}';
    }
}
