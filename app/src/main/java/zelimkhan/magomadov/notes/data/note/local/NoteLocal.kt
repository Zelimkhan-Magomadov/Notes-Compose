package zelimkhan.magomadov.notes.data.note.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "note")
data class NoteLocal(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val title: String = "",
    val text: String = "",
    val type: NoteTypeLocal = NoteTypeLocal.Note
)