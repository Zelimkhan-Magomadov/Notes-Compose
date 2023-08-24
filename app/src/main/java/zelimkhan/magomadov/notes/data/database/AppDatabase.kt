package zelimkhan.magomadov.notes.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import zelimkhan.magomadov.notes.data.note.local.NoteLocal
import zelimkhan.magomadov.notes.data.note.local.NoteLocalDataSource
import zelimkhan.magomadov.notes.data.note.local.NoteTypeConverter

@Database(entities = [NoteLocal::class], version = 1)
@TypeConverters(NoteTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteLocalDataSource
}