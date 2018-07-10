package com.mpls.web2.service.impl;

import com.mpls.web2.config.json.mapper.JsonMapper;
import com.mpls.web2.dto.CoursePageableWrapper;
import com.mpls.web2.dto.CourseShortDto;
import com.mpls.web2.model.Course;
import com.mpls.web2.model.CourseVote;
import com.mpls.web2.model.User;
import com.mpls.web2.model.enums.CourseType;
import com.mpls.web2.repository.CourseRepository;
import com.mpls.web2.service.CourseService;
import com.mpls.web2.service.CourseVoteService;
import com.mpls.web2.service.UserService;
import com.mpls.web2.service.utils.FileBuilder;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.mpls.web2.dto.utils.builder.Builder.map;
import static com.mpls.web2.service.exceptions.Validation.*;
import static java.util.stream.Collectors.toList;

@Service
public class CourseServiceImpl implements CourseService {

    public static final Logger LOGGER = Logger.getLogger(CourseServiceImpl.class);

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private FileBuilder fileBuilder;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseVoteService courseVoteService;

    @Override
    public List<Course> findTop3() {
        List<Course> list = findAllAvailable();
        List<Course> list2 = new ArrayList<>();
        if (list == null || list.size() < 3) {
            return list;
        } else {
            list.sort((p1, p2) -> Long.compare(p2.getRating(), p1.getRating()));
            for (int i = 0; i < 3; i++) {
                list2.add(list.get(i));
            }
            return list2;
        }
    }

    @Override
    public List<Course> findAllAvailable(Pageable pageable) {
        return courseRepository.findAllByAvailable(true, pageable).getContent();
    }

    @Override
    public List<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable).getContent();
    }

    @Override
    public Course findByHeader(String header) {
        checkString(header);
        return courseRepository.findByHeader(header);
    }

    @Override
    public Course findByDescription(String description) {
        checkString(description);
        return courseRepository.findByDescription(description);
    }

    @Override
    public Course findOneAvailable(Long id, Principal principal) {
        checkId(id);
        return setRating(checkIfUserVoted(courseRepository.findByAvailableAndId(true, id), principal));
    }

    @Override
    public Course findOne(Long id, Principal principal) {
        checkId(id);
        LOGGER.info(courseRepository.findOne(id));
        return setRating(checkIfUserVoted(courseRepository.findOne(id), principal));
    }

    @Override
    public Course save(String courseJson, MultipartFile multipartFile) {
        checkJson(courseJson);
        LOGGER.info(courseJson);
        return courseRepository.save(JsonMapper.json(courseJson, Course.class)
                .setAvailable(true)
                .setImage(fileBuilder.saveFile(multipartFile))
                .setCanVote(true)
                .setUserRating(0)
                .setRating(0));
    }

    @Override
    public Course update(String courseJson) {
        checkJson(courseJson);
        Course course = JsonMapper.json(courseJson, Course.class);
        checkUpdate(course.getId(), courseRepository);
        return courseRepository.save(courseRepository.findOne(course.getId())
                .setHeader(course.getHeader())
                .setDescription(course.getDescription())
                .setLink(course.getLink())
                .setType(course.getType())
                .setImage(course.getImage())
                .setCourseCategory(course.getCourseCategory())
                .setAvailable(course.getAvailable())
        );
    }

    @Override
    public Course updateImage(Long courseId, MultipartFile multipartFile) {
        Course course = courseRepository.findOne(courseId);
        try {
            return courseRepository.save(course.setImage(fileBuilder.saveFile(multipartFile)));
        }catch (Exception e){
            return course;
        }
    }

    @Override
    public Boolean delete(Long id) {
        if (id != null || id >= 0) {
            courseRepository.delete(id);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public List<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllAvailable() {
        return courseRepository.findAllByAvailable(true);
    }

    @Override
    public List<Course> findAllByCourseType(CourseType courseType) {
        return courseRepository.findAllByType(courseType);
    }

    @Override
    public List<Course> findAll(Sort sort) {
        return courseRepository.findAll();
    }

    @Override
    public List<Course> findAllByCourseCategoryId(Long id) {
        checkId(id);
        return courseRepository.findAllByCourseCategory_Id(id);
    }

    @Override
    public Course vote(Long courseId, Integer rating, Principal principal) {
        Course course = courseRepository.findOne(courseId);
        User user = userService.findByEmail(principal.getName());
        courseVoteService.save(new CourseVote().setCourse(course).setUser(user).setRating(rating));
        return setRating(checkIfUserVoted(course, principal));
    }

    @Override
    public CoursePageableWrapper findAllByCourseCategoryIdAndType(String name, Integer typeNumber, Pageable pageable) {

        CoursePageableWrapper wrapper = new CoursePageableWrapper()
                .setCurrentPage(pageable.getPageNumber())
                .setNumberOfItems(pageable.getPageSize());
        Integer size = 0;

        if (!name.equals("empty") && typeNumber == -1) {
            size = courseRepository.countAllByCourseCategory_NameAndAvailable(name, true);
            return wrapper.setCourses(courseRepository.findAllByCourseCategory_NameAndAvailable(name, true, pageable).getContent()
            .stream().map(course -> map(course, CourseShortDto.class)).collect(toList()))
                    .setNumberOfPages((size / pageable.getPageSize()) + 1);
        }
        if (name.equals("empty") && typeNumber != -1) {
            size = courseRepository.countAllByTypeAndAvailable(CourseType.values()[typeNumber], true);
            return wrapper.setCourses(courseRepository.findAllByTypeAndAvailable(CourseType.values()[typeNumber], true, pageable).getContent()
            .stream().map(course -> map(course,CourseShortDto.class)).collect(toList()))
                    .setNumberOfPages((size / pageable.getPageSize()) + 1);
        }
        if (!name.equals("empty") && typeNumber != -1) {
            size = courseRepository.countAllByCourseCategory_NameAndTypeAndAvailable(name, CourseType.values()[typeNumber], true);
            return wrapper.setCourses(courseRepository.findAllByCourseCategory_NameAndTypeAndAvailable(name, CourseType.values()[typeNumber], true, pageable).getContent()
            .stream().map(course -> map(course,CourseShortDto.class)).collect(toList()))
                    .setCurrentPage((size/pageable.getPageSize()) + 1);
        }
        size = courseRepository.countAllByAvailable(true);
        return wrapper.setCourses(courseRepository.findAllByAvailable(true, pageable).getContent()
        .stream().map(course -> map(course,CourseShortDto.class)).collect(toList()))
                .setNumberOfPages((size/pageable.getPageSize()) + 1);
    }

    private Course setRating(Course course) {
        List<CourseVote> courseVotes = courseVoteService.findAllByCourseId(course.getId());
        Integer sum = 0;
        Integer count = courseVotes.size();
        if (count == 0) {
            return courseRepository.save(course);
        } else {
            for (CourseVote courseVote : courseVotes) {
                sum += courseVote.getRating();
            }
            LOGGER.info(sum / count);
            return courseRepository.save(course.setRating(sum / count));
        }
    }

    private Course checkIfUserVoted(Course course, Principal principal) {
        if (principal == null || principal.getName() == null) {
            return course;
        } else {
            User user = userService.findByEmail(principal.getName());
            CourseVote courseVote = courseVoteService.findByCourseIdAndUserId(course.getId(), user.getId());
            if (courseVote != null) {
                return course.setCanVote(false).setUserRating(courseVote.getRating());
            } else {
                return course.setCanVote(true).setUserRating(0);
            }
        }
    }

}
