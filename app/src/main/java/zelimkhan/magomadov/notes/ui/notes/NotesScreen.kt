package zelimkhan.magomadov.notes.ui.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zelimkhan.magomadov.notes.ui.notes.components.NoteCategories
import zelimkhan.magomadov.notes.ui.notes.components.NoteItem
import zelimkhan.magomadov.notes.ui.notes.components.SearchNote
import zelimkhan.magomadov.notes.ui.theme.NotesTheme
import kotlin.random.Random

@Composable
fun NotesScreen(
    modifier: Modifier = Modifier,
    notes: List<NoteItemState>,
    noteCategoryState: NoteCategoryState,
    onIntent: (NotesIntent) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        SearchNote(
            value = "",
            onValueChange = {},
            onSearch = {},
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        )

        NoteCategories(noteCategoryState = noteCategoryState, onSelectCategory = onIntent)

        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(notes, key = { item -> item.id }) {
                NoteItem(noteItemState = it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        NotesScreen(
            modifier = Modifier.fillMaxSize(),
            notes = List(10) {
                NoteItemState(
                    id = it.toLong(),
                    title = "Title",
                    text = "Text".repeat(Random.nextInt(50))
                )
            },
            noteCategoryState = NoteCategoryState.Notes,
            onIntent = {}
        )
    }
}