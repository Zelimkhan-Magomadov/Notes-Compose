package zelimkhan.magomadov.notes.ui.note.state

import zelimkhan.magomadov.notes.data.NoteLocal

data class NoteState(
    val id: Long = 0,
    val title: String = "",
    val text: String = "",
    val type: NoteType = NoteType.Note
)

fun NoteLocal.asNoteState(): NoteState {
    return NoteState(
        id = this.id,
        title = this.title,
        text = this.text,
        type = this.type.asNoteType()
    )
}

fun NoteState.asNoteLocal(): NoteLocal {
    return NoteLocal(
        id = this.id,
        title = this.title,
        text = this.text,
        type = this.type.asNoteTypeLocal()
    )
}
