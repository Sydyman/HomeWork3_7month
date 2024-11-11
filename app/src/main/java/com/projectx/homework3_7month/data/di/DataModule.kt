package com.projectx.homework3_7month.data.di

import androidx.room.Room
import com.projectx.homework3_7month.data.database.AppDatabase
import com.projectx.homework3_7month.data.repositoryImpl.TaskManagerRepositoryImpl
import com.projectx.homework3_7month.domain.repository.TaskManagerRepository
import org.koin.core.module.Module
import org.koin.dsl.module

val dataModules: Module = module {

    single {

        Room.databaseBuilder(get(), AppDatabase::class.java,"TaskDataBase")
            .fallbackToDestructiveMigration()
            .build()

    }

    single { get  <AppDatabase>().taskManagerDao() }

    single  <TaskManagerRepository>{ TaskManagerRepositoryImpl(get()) }

}