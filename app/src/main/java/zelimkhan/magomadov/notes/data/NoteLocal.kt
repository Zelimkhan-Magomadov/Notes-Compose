package zelimkhan.magomadov.notes.data

data class NoteLocal(
    val id: Long = 0,
    val title: String = "",
    val text: String = "",
    val noteType: NoteType = NoteType.Note
)
