package org.adex.kotlingdemo.repositories

import org.adex.kotlingdemo.entities.Course
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface CourseRepository : CrudRepository<Course, Int>