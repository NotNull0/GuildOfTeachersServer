package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

@Dto
public class TeachingProgramFullDto extends TeachingProgramShortDto<TeachingProgramFullDto>{

    private TeachingProgramContainerShortDto container;

    public TeachingProgramContainerShortDto getContainer() {
        return container;
    }

    public TeachingProgramFullDto setContainer(TeachingProgramContainerShortDto container) {
        this.container = container;
        return this;
    }

    @Override
    public String toString() {
        return "TeachingProgramFullDto{" +
                "container=" + container.getId() +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", available=" + available +
                ", file=" + file.getId() +
                '}';
    }
}
