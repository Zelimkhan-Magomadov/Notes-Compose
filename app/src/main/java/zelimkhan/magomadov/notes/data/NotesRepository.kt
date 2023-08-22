package zelimkhan.magomadov.notes.data

class NotesRepository {
    val notes = mutableListOf(
        NoteLocal(id = 0, "Note", "text", noteType = NoteType.Note),
        NoteLocal(id = 1, "Archived", "text", noteType = NoteType.Archived),
        NoteLocal(id = 2, "Deleted", "text", noteType = NoteType.Deleted),
    )

    fun getNotes(noteType: NoteType): List<NoteLocal> {
        return notes.filter { it.noteType == noteType }
    }
}