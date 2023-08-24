package zelimkhan.magomadov.notes.data.note.local

import androidx.room.TypeConverter

class NoteTypeConverter {
    @TypeConverter
    fun fromNoteType(noteType: NoteTypeLocal): String {
        return noteType.toString()
    }

    @TypeConverter
    fun toNoteType(value: String): NoteTypeLocal {
        return when (value) {
            NoteTypeLocal.Note.toString() -> NoteTypeLocal.Note
            NoteTypeLocal.Archived.toString() -> NoteTypeLocal.Archived
            NoteTypeLocal.Deleted.toString() -> NoteTypeLocal.Deleted
            else -> throw IllegalArgumentException("Unknown NoteTypeLocal: $value")
        }
    }
}