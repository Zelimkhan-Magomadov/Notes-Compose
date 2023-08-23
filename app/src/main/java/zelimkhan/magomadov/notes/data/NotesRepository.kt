package zelimkhan.magomadov.notes.data

class NotesRepository {
    companion object {
        val notes = mutableListOf(
            NoteLocal(id = 1, "Note", "text", type = NoteTypeLocal.Note),
            NoteLocal(id = 2, "Archived", "text", type = NoteTypeLocal.Archived),
            NoteLocal(id = 3, "Deleted", "text", type = NoteTypeLocal.Deleted),
        )
    }

    fun getNotes(noteTypeLocal: NoteTypeLocal): List<NoteLocal> {
        return notes.filter { it.type == noteTypeLocal }
    }

    fun getNote(id: Long): NoteLocal {
        return notes.first { it.id == id }
    }

    fun createNote(): NoteLocal {
        val newNoteId = notes.count() + 1L
        val newNote = NoteLocal(id = newNoteId)
        notes.add(newNote)
        return newNote
    }

    fun save(noteLocal: NoteLocal) {
        val existingNoteIndex = notes.indexOfFirst { it.id == noteLocal.id }

        if (existingNoteIndex != -1)
            notes[existingNoteIndex] = noteLocal
        else
            notes.add(noteLocal)
    }
}