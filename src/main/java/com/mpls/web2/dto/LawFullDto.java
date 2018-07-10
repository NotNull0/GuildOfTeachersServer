package com.mpls.web2.dto;

import com.mpls.web2.dto.utils.annotations.Dto;

@Dto
public class LawFullDto extends LawShortDto<LawFullDto> {

    private LawContainerShortDto container;

    public LawContainerShortDto getContainer() {
        return container;
    }

    public LawFullDto setContainer(LawContainerShortDto container) {
        this.container = container;
        return this;
    }

    @Override
    public String toString() {
        return "LawFullDto{" +
                "container=" + container.getId() +
                ", id=" + id +
                ", datetime=" + datetime +
                ", name='" + name + '\'' +
                ", path='" + path + '\'' +
                ", available=" + available +
                '}';
    }
}
