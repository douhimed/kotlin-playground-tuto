package org.adex.kotlingdemo.services.impl

import org.adex.kotlingdemo.utils.CourseException
import org.adex.kotlingdemo.dtos.CourseDto
import org.adex.kotlingdemo.entities.Course
import org.adex.kotlingdemo.repositories.CourseRepository
import org.adex.kotlingdemo.services.CourseServices
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.Objects

@Service
class CourseServicesImpl(
    val courseRepository: CourseRepository
) : CourseServices {

    override fun createNewCourse(courseDto: CourseDto): Int {
        Objects.requireNonNull(courseDto)

        val course = courseDto.let {
            Course(null, it.title, it.category)
        }

        return courseRepository.save(course).id
            ?: throw RuntimeException("Something went wrong")
    }

    override fun getCourses(title: String?): List<CourseDto> {

        var courses = title?.let {
            courseRepository.findByTitleContainsIgnoreCase(title)
        } ?: courseRepository.findAll()

        return courses
            .map { CourseDto(it.id, it.title, it.category) }
    }

    override fun updateCourse(id: Int, courseDto: CourseDto): Int {
        val oldCourse = courseRepository.findById(id)
            .orElseThrow { CourseException("The course with the given id $id not found") }

        return oldCourse.let {
            it.title = courseDto.title
            it.category = courseDto.category
            courseRepository.save(it)
            id
        }
    }

    override fun deleteCourseById(id: Int) {
        val oldCourse = courseRepository.findById(id)
            .orElseThrow { CourseException("The course with the given id $id not found") }

        oldCourse.let {
            courseRepository.deleteById(id)
        }
    }
}