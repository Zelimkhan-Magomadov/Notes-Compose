package zelimkhan.magomadov.notes.ui.notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zelimkhan.magomadov.notes.ui.notes.state.NoteItemState
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteItem(
    noteItemState: NoteItemState,
    modifier: Modifier = Modifier,
    onClick: (noteId: Long) -> Unit
) {
    Card(modifier = modifier, onClick = { onClick(noteItemState.id) }) {
        Column(modifier = Modifier.padding(12.dp)) {
            Text(
                text = noteItemState.title,
                style = MaterialTheme.typography.titleMedium,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = noteItemState.text,
                style = MaterialTheme.typography.bodySmall,
                maxLines = 10,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        Box(modifier = Modifier.padding(16.dp)) {
            NoteItem(
                noteItemState = NoteItemState(
                    title = "Note title",
                    text = "Note text"
                ),
                onClick = {}
            )
        }
    }
}