package com.admiral26.noteapponline1.di

import com.admiral26.noteapponline1.core.repository.NoteRepository
import com.admiral26.noteapponline1.core.repository.NoteRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindNoteRepository(repositoryImp: NoteRepositoryImp):NoteRepository
}