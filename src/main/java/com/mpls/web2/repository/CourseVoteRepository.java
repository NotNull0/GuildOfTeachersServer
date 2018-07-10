package com.mpls.web2.repository;

import com.mpls.web2.model.CourseVote;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseVoteRepository extends JpaRepository<CourseVote, Long> {

    List<CourseVote> findAllByUser_Id(Long id);

    List<CourseVote> findAllByCourse_Id(Long id);

    List<CourseVote> findAllByRating(Integer rating);

    CourseVote findByAvailableAndId(Boolean available, Long id);

    List<CourseVote> findAllByAvailable(Boolean available);

    CourseVote findByCourse_IdAndUser_Id(Long courseId, Long userId);
}
