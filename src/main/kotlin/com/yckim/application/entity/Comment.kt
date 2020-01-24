package com.yckim.application.entity

import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Column
import org.springframework.data.relational.core.mapping.Table
import java.time.LocalDateTime

@Table(value = "comment")
data class Comment(
    @Id
    val id: Long?,
    @Column(value = "content")
    val content: String,
    @Column(value = "board_id")
    val boardId: Long,
    @Column(value = "user_id")
    val userId: Long,
    @CreatedDate
    @Column(value = "created_at")
    val createdAt: LocalDateTime = LocalDateTime.now()
)