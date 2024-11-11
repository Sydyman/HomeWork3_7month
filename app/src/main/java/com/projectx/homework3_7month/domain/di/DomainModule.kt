package com.projectx.homework3_7month.domain.di

import com.projectx.homework3_7month.domain.usecase.InsertTaskUseCase
import com.projectx.homework3_7month.domain.usecase.UpdateTaskUseCase
import org.koin.core.module.Module
import org.koin.dsl.module

val domainModule: Module = module {

    factory { InsertTaskUseCase(get()) }
    single { UpdateTaskUseCase(get()) }
}