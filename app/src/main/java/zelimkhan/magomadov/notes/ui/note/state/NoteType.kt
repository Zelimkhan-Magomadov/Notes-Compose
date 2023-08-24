package zelimkhan.magomadov.notes.ui.note.state

import zelimkhan.magomadov.notes.data.note.local.NoteTypeLocal

sealed interface NoteType {
    data object Note : NoteType
    data object Archived : NoteType
    data object Deleted : NoteType
}

fun NoteType.asNoteTypeLocal(): NoteTypeLocal {
    return when (this) {
        NoteType.Note ->  NoteTypeLocal.Note
        NoteType.Archived -> NoteTypeLocal.Archived
        NoteType.Deleted -> NoteTypeLocal.Deleted
    }
}

fun NoteTypeLocal.asNoteType(): NoteType {
    return when (this) {
        NoteTypeLocal.Note -> NoteType.Note
        NoteTypeLocal.Archived -> NoteType.Archived
        NoteTypeLocal.Deleted -> NoteType.Deleted
    }
}