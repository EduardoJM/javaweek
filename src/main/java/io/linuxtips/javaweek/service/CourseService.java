package io.linuxtips.javaweek.service;

import io.linuxtips.javaweek.model.Course;
import io.linuxtips.javaweek.repository.CourseRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {
    private final CourseRepository courseRepository;

    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public Course createCourse(Course course) {
        return this.courseRepository.save(course);
    }

    public List<Course> listCourses() {
        return this.courseRepository.findAll();
    }

    public ResponseEntity<Course> getById(Long id) {
        return this.courseRepository.findById(id)
                .map(course -> ResponseEntity.ok().body(course))
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Course> updateById(Long id, Course course) {
        return this.courseRepository.findById(id)
                .map(courseToUpdate -> {
                    courseToUpdate.setName(course.getName());
                    courseToUpdate.setPrice(course.getPrice());

                    Course updated = this.courseRepository.save(courseToUpdate);
                    return ResponseEntity.ok().body(updated);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Object> deleteById(Long id) {
        return this.courseRepository.findById(id)
                .map(course -> {
                    this.courseRepository.deleteById(id);

                    return ResponseEntity.noContent().build();
                }).orElse(ResponseEntity.notFound().build());
    }
}
