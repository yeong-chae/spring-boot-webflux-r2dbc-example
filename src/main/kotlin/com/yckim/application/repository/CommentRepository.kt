package com.yckim.application.repository

import com.yckim.application.entity.Comment
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CommentRepository : ReactiveCrudRepository<Comment, Long> {

    @Query("""
        select c.* from Comment c where c.board_id = :boardId
    """)
    fun findAllByBoardId(boardId: Long): Flux<Comment>
}