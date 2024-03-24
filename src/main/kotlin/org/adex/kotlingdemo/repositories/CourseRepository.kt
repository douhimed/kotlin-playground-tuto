package org.adex.kotlingdemo.repositories

import org.adex.kotlingdemo.entities.Course
import org.adex.kotlingdemo.services.CourseServices
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Int> {

    fun findByTitleContainsIgnoreCase(title: String) : List<Course>

    @Query(value = "SELECT * FROM COURSE WHERE title LIKE %?1%", nativeQuery = true)
    fun findCoursesByTitle(title: String): List<Course>

}