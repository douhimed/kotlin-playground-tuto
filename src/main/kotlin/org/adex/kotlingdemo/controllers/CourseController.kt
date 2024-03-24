package org.adex.kotlingdemo.controllers

import jakarta.validation.Valid
import org.adex.kotlingdemo.dtos.CourseDto
import org.adex.kotlingdemo.services.CourseServices
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/courses")
@Validated
class CourseController(val courseServices: CourseServices) {

    @PostMapping
    fun create(@RequestBody @Valid courseDto: CourseDto): ResponseEntity<Int> {
        val id = courseServices.createNewCourse(courseDto)
        return ResponseEntity.ok(id)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllCourses(@RequestParam(required = false) title: String?): ResponseEntity<List<CourseDto>> {
        var courses = courseServices.getCourses(title)
        return ResponseEntity.ok(courses)
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun updateCourse(
        @RequestBody @Valid courseDto: CourseDto,
        @PathVariable id: Int
    ): ResponseEntity<Int> {
        return ResponseEntity.ok(courseServices.updateCourse(id, courseDto))
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: Int) {
        courseServices.deleteCourseById(id)
    }
}