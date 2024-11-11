package com.projectx.homework3_7month.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy.Companion.REPLACE
import androidx.room.Query
import androidx.room.Update
import com.projectx.homework3_7month.data.dto.TaskDto

@Dao
interface TaskManagerDao {

    @Insert(onConflict = REPLACE )
    suspend fun insertTask(taskDto: TaskDto)

    @Query("SELECT * FROM taskdto")
    suspend fun getAllNotes(): List<TaskDto>


    @Query("SELECT * FROM taskdto WHERE taskName = :taskName LIMIT 1")
    suspend fun getTaskByName(taskName: String): TaskDto?

    @Update
    suspend fun updateTask(task: TaskDto)

    @Delete
    suspend fun deleteTask(task:TaskDto)
}