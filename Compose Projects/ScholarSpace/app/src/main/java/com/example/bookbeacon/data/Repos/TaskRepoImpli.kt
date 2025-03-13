package com.example.bookbeacon.data.Repos

import com.example.bookbeacon.domain.model.Task
import com.example.studysmart.data.local.TaskDao
import com.example.studysmart.domain.repository.TaskRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class TaskRepoImpli @Inject constructor(
    private val taskDao: TaskDao
) : TaskRepository {
    override suspend fun upsertTask(task: Task) {
        taskDao.upsertTask(task)
    }

    override suspend fun deleteTask(taskId: Int) {
        TODO("Not yet implemented")
    }

    override suspend fun getTaskById(taskId: Int): Task? {
        TODO("Not yet implemented")
    }

    override fun getUpcomingTasksForSubject(subjectId: Int): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getCompletedTasksForSubject(subjectId: Int): Flow<List<Task>> {
        TODO("Not yet implemented")
    }

    override fun getAllUpcomingTasks(): Flow<List<Task>> {
        TODO("Not yet implemented")
    }
}