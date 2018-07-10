package com.mpls.web2.repository;

import com.mpls.web2.model.enums.FileType;
import com.mpls.web2.model.File;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FileRepository extends JpaRepository<File, Long> {

    File findByName(String name);

    List<File> findAllByFileType(FileType type);

    File findByAvailableAndId(Boolean available, Long id);

    List<File> findAllByAvailable(Boolean available);

    File findByPath(String path);

}
