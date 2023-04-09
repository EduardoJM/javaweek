package io.linuxtips.javaweek.controller;

import io.linuxtips.javaweek.model.Course;
import io.linuxtips.javaweek.service.CourseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
@Tag(name="courses", description = "The courses API")
public class CourseController {
    private final CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create a course", description = "")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "201",
            description = "Course created",
            content = @Content(schema = @Schema(implementation=Course.class))
        ),
    })
    public Course create(@RequestBody Course course) {
        return this.courseService.createCourse(course);
    }

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Find all courses", description = "")
    public List<Course> list() {
        return this.courseService.listCourses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get course by id", description = "")
    @ApiResponses(value = {
        @ApiResponse(
            responseCode = "200",
            description = "Sucessfull operation",
            content = @Content(schema = @Schema(implementation=Course.class))
        ),
        @ApiResponse(
            responseCode = "404",
            description = "Course not found",
            content = @Content
        )
    })
    public ResponseEntity<Course> getById(@PathVariable("id") Long id) {
        return this.courseService.getById(id);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update course by id", description = "")
    public ResponseEntity<Course> updateById(@PathVariable("id") Long id, @RequestBody Course course) {
        return this.courseService.updateById(id, course);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete course by id", description = "")
    public ResponseEntity<Object> deleteById(@PathVariable("id") Long id) {
        return this.courseService.deleteById(id);
    }
}
