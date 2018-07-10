package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class TeachingProgramContainerFullDto extends TeachingProgramContainerShortDto<TeachingProgramContainerFullDto> {

    List<TeachingProgramShortDto> programs;

    public List<TeachingProgramShortDto> getPrograms() {
        return programs;
    }

    public TeachingProgramContainerFullDto setPrograms(List<TeachingProgramShortDto> programs) {
        this.programs = programs;
        return this;
    }

    @Override
    public String toString() {
        return "TeachingProgramContainerFullDto{" +
                "programs=" + programs.stream().map(TeachingProgramShortDto::getId).collect(toList()) +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
