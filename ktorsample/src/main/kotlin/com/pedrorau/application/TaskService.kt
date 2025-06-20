package com.pedrorau.application

import com.pedrorau.domain.models.Task

object TaskService {
    private val tasks = mutableListOf<Task>()

    fun getAllTasks(): List<Task> = tasks

    fun createTask(task: Task): Task {
        tasks.add(task)
        return task
    }
}