package org.adex.kotlingdemo.entities

import jakarta.persistence.*

@Entity
@Table(name = "course")
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Int?,
    var title: String,
    var category: String
)