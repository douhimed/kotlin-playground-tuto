package org.adex.kotlingdemo.services

import org.adex.kotlingdemo.dtos.CourseDto

interface CourseServices {
    fun createNewCourse(courseDto: CourseDto): Int
    fun getAllCourses():  List<CourseDto>
    fun updateCourse(id: Int, courseDto: CourseDto): Int
    fun deleteCourseById(id: Int)
}