package com.yckim.application.repository

import com.yckim.application.entity.Board
import org.springframework.data.repository.reactive.ReactiveCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface BoardRepository : ReactiveCrudRepository<Board, Long>