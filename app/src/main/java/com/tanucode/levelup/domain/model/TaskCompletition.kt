package com.tanucode.levelup.domain.model

import java.time.Instant

data class TaskCompletition(
    val taskId : Long,
    val completedAt : Instant
)
