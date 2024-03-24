package org.adex.kotlingdemo.dtos

import jakarta.validation.constraints.NotBlank

data class CourseDto(
    val id: Int?,

    @get:NotBlank(message = "courseDto.title must not be blank")
    val title: String,

    @get:NotBlank(message = "courseDto.category must not be blank")
    val category: String
)