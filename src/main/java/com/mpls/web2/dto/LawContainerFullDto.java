package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Dto
public class LawContainerFullDto extends LawContainerShortDto<LawContainerFullDto>{

    private List<LawShortDto> laws;

    public List<LawShortDto> getLaws() {
        return laws;
    }

    public LawContainerFullDto setLaws(List<LawShortDto> laws) {
        this.laws = laws;
        return this;
    }

    @Override
    public String toString() {
        return "LawContainerFullDto{" +
                "laws=" + laws.stream().map(LawShortDto::getId).collect(toList()) +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                '}';
    }
}
