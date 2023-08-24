package zelimkhan.magomadov.notes.data.note.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteLocalDataSource {
    @Query("SELECT * FROM note WHERE type LIKE :type")
    fun get(type: NoteTypeLocal): Flow<List<NoteLocal>>

    @Query("SELECT * FROM note WHERE id LIKE :id")
    suspend fun get(id: Long): NoteLocal

    @Insert
    suspend fun add(note: NoteLocal): Long

    @Update
    suspend fun update(note: NoteLocal)
}