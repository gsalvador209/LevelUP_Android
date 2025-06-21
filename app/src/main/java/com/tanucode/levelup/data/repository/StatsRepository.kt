package com.tanucode.levelup.data.repository

import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId

interface StatsRepository {
    suspend fun  getHeatmapData(listId: Long) : Map<LocalDate, Int>
}

class StatsRepositoryImpl(
    private val taskRepository: TaskRepository
) : StatsRepository {
    override suspend fun getHeatmapData(listId: Long): Map<LocalDate, Int> {
        val taskWithCompletitions = taskRepository.getCompletionsByList(listId)
        return taskWithCompletitions
            .flatMap { it.completions }
            .map { completion ->
                completion.completedAt.toInstant().atZone(ZoneId.systemDefault())
            }
            .groupBy { it }
            .count()
    }
}