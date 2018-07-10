package com.mpls.web2.service;

import com.mpls.web2.model.CourseVote;

import java.util.List;

public interface CourseVoteService {

    List<CourseVote> findAllByUserId(Long id);

    List<CourseVote> findAllByCourseId(Long id);

    CourseVote findByCourseIdAndUserId(Long courseId, Long userId);

    CourseVote save(CourseVote courseVote);

    CourseVote update(CourseVote courseVote);

    CourseVote findOne(Long id);

    List<CourseVote> findAll();

    Boolean delete(Long id);

    Boolean delete(Iterable<? extends CourseVote> courseVotes);

    CourseVote findOneAvailable(Long id);

    List<CourseVote> findAllAvailable();

}
