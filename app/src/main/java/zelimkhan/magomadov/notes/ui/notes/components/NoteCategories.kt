package zelimkhan.magomadov.notes.ui.notes.components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import zelimkhan.magomadov.notes.R
import zelimkhan.magomadov.notes.ui.notes.NoteCategoryState
import zelimkhan.magomadov.notes.ui.notes.NotesIntent

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NoteCategories(
    modifier: Modifier = Modifier,
    noteCategoryState: NoteCategoryState,
    onSelectCategory: (NotesIntent) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .horizontalScroll(rememberScrollState()),
        horizontalArrangement = Arrangement.spacedBy(
            space = 16.dp,
            alignment = Alignment.CenterHorizontally
        )
    ) {
        FilterChip(
            selected = noteCategoryState is NoteCategoryState.Notes,
            onClick = { onSelectCategory(NotesIntent.SelectNotesCategory) },
            label = { Text(text = stringResource(R.string.notes)) })
        FilterChip(
            selected = noteCategoryState is NoteCategoryState.Archive,
            onClick = { onSelectCategory(NotesIntent.SelectArchiveCategory) },
            label = { Text(text = stringResource(R.string.archive)) })
        FilterChip(
            selected = noteCategoryState is NoteCategoryState.Trash,
            onClick = { onSelectCategory(NotesIntent.SelectTrashCategory) },
            label = { Text(text = stringResource(R.string.trash)) })
    }
}