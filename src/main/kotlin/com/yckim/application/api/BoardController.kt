package com.yckim.application.api

import com.yckim.application.entity.Board
import com.yckim.application.repository.BoardRepository
import com.yckim.application.repository.CommentRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/board")
class BoardController(
    private val boardRepository: BoardRepository,
    private val commentRepository: CommentRepository
) {

    @PostMapping
    fun create(
        @RequestBody board: Board
    ) = boardRepository.save(board)

    @GetMapping
    fun getBoards() = boardRepository.findAll()

    @GetMapping("/{id}")
    fun getOne(@PathVariable id: Long) = boardRepository.findById(id)

    @GetMapping("/{id}/comment")
    fun getComments(@PathVariable id: Long) = commentRepository.findAllByBoardId(id)

}