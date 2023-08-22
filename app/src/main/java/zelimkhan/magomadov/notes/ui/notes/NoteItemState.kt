package zelimkhan.magomadov.notes.ui.notes

import zelimkhan.magomadov.notes.data.NoteLocal

data class NoteItemState(
    val id: Long = 0,
    val title: String = "",
    val text: String = ""
)

fun NoteLocal.asNoteItem(): NoteItemState {
    return NoteItemState(
        id = this.id,
        title = this.title,
        text = this.text
    )
}