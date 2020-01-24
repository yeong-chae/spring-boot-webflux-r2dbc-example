package com.yckim.application.config

import com.yckim.application.entity.Board
import com.yckim.application.entity.Comment
import com.yckim.application.entity.User
import com.yckim.application.repository.BoardRepository
import com.yckim.application.repository.CommentRepository
import com.yckim.application.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.data.r2dbc.core.DatabaseClient
import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import java.util.stream.Stream

@Component
class DatabaseInitianlizer {

    @Bean
    fun init(
        db: DatabaseClient,
        userRepository: UserRepository,
        boardRepository: BoardRepository,
        commentRepository: CommentRepository
    ) = ApplicationRunner {
        val initTable = db.execute(
            """
                create table user (
                    id bigint generated by default as identity,
                    name VARCHAR(200) NOT NULL
                );
                
                create table board (
                    id bigint generated by default as identity,
                    content VARCHAR(200) NOT NULL,
                    user_id int NOT NULL,
                    created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP
                );
                
                create table comment (
                    id bigint generated by default as identity,
                    content VARCHAR(200) NOT NULL,
                    board_id int NOT NULL,
                    user_id int NOT NULL,
                    created_at timestamp NULL DEFAULT CURRENT_TIMESTAMP
                );
            """.trimIndent()
        )

        val initUserStream = Stream.of(
            User(null, "yckim"),
            User(null,"jack")
        )

        val initBoardStream = Stream.of(
            Board(null, "title1", 1),
            Board(null, "title2", 2)
        )

        val initCommentStream = Stream.of(
            Comment(null, "comment1", 1, 2),
            Comment(null, "comment2", 1, 2),
            Comment(null, "comment3", 1, 2)
        )

        val saveAllUser = userRepository.saveAll(Flux.fromStream(initUserStream))
        val saveAllBoard = boardRepository.saveAll(Flux.fromStream(initBoardStream))
        val saveAllComment = commentRepository.saveAll(Flux.fromStream(initCommentStream))

        initTable
                .then()
                .thenMany(saveAllUser)
                .thenMany(saveAllBoard)
                .thenMany(saveAllComment)
                .subscribe()
    }
}