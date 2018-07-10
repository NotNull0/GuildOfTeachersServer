package com.mpls.web2.service.impl;

import com.mpls.web2.model.Comment;
import com.mpls.web2.repository.CommentRepository;
import com.mpls.web2.service.ArticleService;
import com.mpls.web2.service.CommentService;
import com.mpls.web2.service.CourseService;
import com.mpls.web2.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

import static com.mpls.web2.service.exceptions.Validation.checkId;
import static com.mpls.web2.service.exceptions.Validation.checkSave;
import static com.mpls.web2.service.exceptions.Validation.checkUpdate;

@Service
public class CommentServiceImpl implements CommentService {

    private final static Logger LOGGER = Logger.getLogger(CommentServiceImpl.class);

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Override
    public List<Comment> findAllByFromId(Long id) {
        checkId(id);
        return commentRepository.findAllByFrom_Id(id);
    }

    @Override
    public Comment findOneAvailable(Long id) {
        checkId(id);
        return commentRepository.findByAvailableAndId(true, id);
    }

    @Override
    public List<Comment> findAllAvailable() {
        return commentRepository.findAllByAvailable(true);
    }

    @Override
    public Comment findOne(Long id) {
        checkId(id);
        return commentRepository.findOne(id);
    }

    @Override
    public Comment save(Comment comment) {
        checkSave(comment);
        return commentRepository.save(comment.setDatetime(Timestamp.valueOf(LocalDateTime.now())));
    }

    @Override
    public Comment saveArticle(Comment comment, Long id, Principal principal) {
        if (principal != null) {
            if (principal.getName() != null) {
                comment.setArticle(articleService.findOne(id));
                comment.setFrom(userService.findByEmail(principal.getName()));
                return save(comment);
            }
        }
        throw new NullPointerException();
    }

    @Override
    public Comment saveCourse(Comment comment, Long id, Principal principal) {
        if (principal != null) {
            if (principal.getName() != null) {
                comment.setCourse(courseService.findOne(id, principal));
                comment.setFrom(userService.findByEmail(principal.getName()));
                return save(comment);
            }
        }
        throw new NullPointerException();
    }

    @Override
    public Comment update(Comment comment) {
        checkUpdate(comment.getId(), commentRepository);
        return commentRepository.save(findOne(comment.getId()).setAvailable(comment.getAvailable()));
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            Comment comment = commentRepository.findOne(id);
            if (comment != null) {
                commentRepository.delete(comment);
                return true;
            } else {
                return false;
            }

        } else {
            throw new NullPointerException("id must be not null");
        }
    }

    @Override
    public List<Comment> findAll() {
        LOGGER.info("------------------COMMENT-------FIND-ALL----------------");
        List<Comment> comments = commentRepository.findAll();
        LOGGER.info("--------END----------COMMENT-------FIND-ALL----------END-------");
        return comments;
    }

    @Override
    public Boolean delete(Iterable<? extends Comment> comments) {
        try {
            commentRepository.delete(comments);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}
