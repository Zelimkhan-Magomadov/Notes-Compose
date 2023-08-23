package zelimkhan.magomadov.notes.ui.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import zelimkhan.magomadov.notes.ui.destinations.NoteScreenDestination
import zelimkhan.magomadov.notes.ui.notes.components.NoteCategories
import zelimkhan.magomadov.notes.ui.notes.components.NoteItem
import zelimkhan.magomadov.notes.ui.notes.components.SearchNote
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

@Destination(start = true)
@Composable
fun NotesScreen(navigator: DestinationsNavigator) {
    val viewModel: NotesViewModel = viewModel()
    val notes = viewModel.notes.collectAsState()
    val noteCategoryState = viewModel.noteCategoryState.collectAsState()

    NotesScreenContent(
        notes = notes.value,
        noteCategoryState = noteCategoryState.value,
        onIntent = viewModel::onIntent,
        navigator = navigator
    )
}

@Composable
fun NotesScreenContent(
    modifier: Modifier = Modifier,
    notes: List<NoteItemState>,
    noteCategoryState: NoteCategoryState,
    onIntent: (NotesIntent) -> Unit,
    navigator: DestinationsNavigator
) {
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { navigator.navigate(NoteScreenDestination()) }) {
                Icon(imageVector = Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) { paddingValues ->
        Column(
            modifier = modifier.padding(paddingValues)
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
                    NoteItem(
                        noteItemState = it,
                        onClick = { noteId -> navigator.navigate(NoteScreenDestination(noteId)) }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        val notes = mutableListOf(
            NoteItemState(id = 1, "Note", "text"),
            NoteItemState(id = 2, "Archived", "text"),
            NoteItemState(id = 3, "Deleted", "text"),
        )

        NotesScreenContent(
            notes = notes,
            noteCategoryState = NoteCategoryState.Notes,
            onIntent = {},
            navigator = EmptyDestinationsNavigator
        )
    }
}