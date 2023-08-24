package zelimkhan.magomadov.notes.ui.notes

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.EmptyDestinationsNavigator
import zelimkhan.magomadov.notes.R
import zelimkhan.magomadov.notes.ui.destinations.NoteScreenDestination
import zelimkhan.magomadov.notes.ui.note.components.NoteTextField
import zelimkhan.magomadov.notes.ui.notes.components.NoteCategories
import zelimkhan.magomadov.notes.ui.notes.components.NoteItem
import zelimkhan.magomadov.notes.ui.notes.state.NoteCategoryState
import zelimkhan.magomadov.notes.ui.notes.state.NoteItemState
import zelimkhan.magomadov.notes.ui.notes.state.NotesIntent
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

@Destination(start = true)
@Composable
fun NotesScreen(navigator: DestinationsNavigator) {
    val viewModel: NotesViewModel = hiltViewModel()
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
            NoteTextField(
                modifier = Modifier.fillMaxWidth(),
                value = "",
                onValueChange = {},
                paddings = PaddingValues(16.dp),
                contentPaddings = PaddingValues(9.dp),
                placeholderText = stringResource(id = R.string.search_notes),
                shape = RoundedCornerShape(32.dp),
                containerColor = MaterialTheme.colorScheme.surfaceVariant,
                leadingIcon = { Icon(Icons.Rounded.Search, null) },
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { /*onSearch()*/ }),
            )

            NoteCategories(noteCategoryState = noteCategoryState, onSelectCategory = onIntent)

            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Fixed(2),
                contentPadding = PaddingValues(8.dp),
                verticalItemSpacing = 8.dp,
                horizontalArrangement = Arrangement.spacedBy(8.dp),
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
    NotesTheme(useDarkTheme = false) {
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