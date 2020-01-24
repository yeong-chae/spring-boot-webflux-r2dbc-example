package com.yckim.application.entity

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table

@Table(value = "user")
data class User(
    @Id
    val id: Long?,
    @Column(value = "name")
    val name: String
)