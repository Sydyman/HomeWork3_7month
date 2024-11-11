package com.projectx.homework3_7month.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.projectx.homework3_7month.data.database.dao.TaskManagerDao
import com.projectx.homework3_7month.data.dto.TaskDto

@Database(entities = [TaskDto::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {

    abstract fun taskManagerDao(): TaskManagerDao

}