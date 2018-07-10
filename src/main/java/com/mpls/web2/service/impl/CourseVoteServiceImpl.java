package com.mpls.web2.service.impl;

import com.mpls.web2.model.CourseVote;
import com.mpls.web2.repository.CourseVoteRepository;
import com.mpls.web2.service.CourseVoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.*;

@Service
public class CourseVoteServiceImpl implements CourseVoteService {

    @Autowired
    private CourseVoteRepository courseVoteRepository;

    @Override
    public List<CourseVote> findAllByUserId(Long id) {
        checkId(id);
        return courseVoteRepository.findAllByUser_Id(id);
    }

    @Override
    public List<CourseVote> findAllByCourseId(Long id) {
        checkId(id);
        return courseVoteRepository.findAllByCourse_Id(id);
    }

    @Override
    public CourseVote save(CourseVote courseVote) {
        checkSave(courseVote);
        return courseVoteRepository.save(courseVote);
    }

    @Override
    public CourseVote update(CourseVote courseVote) {
        checkUpdate(courseVote.getId(), courseVoteRepository);
        return courseVoteRepository.save(courseVote);
    }

    @Override
    public CourseVote findOne(Long id) {
        checkId(id);
        return courseVoteRepository.findOne(id);
    }

    @Override
    public List<CourseVote> findAll() {
        return courseVoteRepository.findAll();
    }

    @Override
    public Boolean delete(Long id) {
        checkId(id);
        CourseVote courseVote = courseVoteRepository.findOne(id);
        if (courseVote != null) {
            courseVoteRepository.delete(courseVote);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Boolean delete(Iterable<? extends CourseVote> courseVotes) {
        try{
            courseVoteRepository.delete(courseVotes);
            return true;
        }catch(Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CourseVote findOneAvailable(Long id) {
        checkId(id);
        return courseVoteRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<CourseVote> findAllAvailable() {
        return courseVoteRepository.findAllByAvailable(true);
    }

    @Override
    public CourseVote findByCourseIdAndUserId(Long courseId, Long userId) {
        checkId(courseId);
        checkId(userId);
        return courseVoteRepository.findByCourse_IdAndUser_Id(courseId, userId);
    }
}
