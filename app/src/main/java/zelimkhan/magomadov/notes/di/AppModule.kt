package zelimkhan.magomadov.notes.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import zelimkhan.magomadov.notes.data.database.AppDatabase
import zelimkhan.magomadov.notes.data.note.NoteRepository
import zelimkhan.magomadov.notes.data.note.local.NoteLocalDataSource
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database").build()
    }

    @Provides
    @Singleton
    fun provideNoteLocalDataSource(database: AppDatabase): NoteLocalDataSource {
        return database.noteDao()
    }

    @Provides
    @Singleton
    fun provideRepository(localDataSource: NoteLocalDataSource): NoteRepository {
        return NoteRepository(localDataSource)
    }
}
