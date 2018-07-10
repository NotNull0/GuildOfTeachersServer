package com.mpls.web2.dto;
import java.util.List;

import static java.util.stream.Collectors.toList;

public class UserFullDto extends UserShortDto<UserFullDto> {

    private List<FileDto> files;
    private String information;

    public String getInformation() {
        return information;
    }

    public UserFullDto setInformation(String information) {
        this.information = information;
        return this;
    }
    
    public List<FileDto> getFiles(){
        return files;   
    }
    
    public UserFullDto setFiles(List<FileDto> files){
        this.files=files;
        return this;
    }

    @Override
    public String toString() {
        return "UserFullDto{" +
                "files=" + files.stream().map(FileDto::getId).collect(toList()) +
                ", information='" + information + '\'' +
                ", incumbency=" + incumbency +
                ", role=" + role +
                ", available=" + available +
                ", placeOfWork=" + placeOfWork.getName() +
                ", specializations=" + specializations.stream().map(SpecializationShortDto::getId).collect(toList()) +
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
