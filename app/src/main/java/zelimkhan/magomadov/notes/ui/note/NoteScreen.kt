package zelimkhan.magomadov.notes.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import zelimkhan.magomadov.notes.R
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

@Destination(navArgsDelegate = NoteScreenNavArgs::class)
@Composable
fun NoteScreen() {
    val viewModel: NoteViewModel = viewModel()
    val noteState = viewModel.noteState.collectAsState()

    NoteScreenContent(
        noteState = noteState.value,
        onIntent = viewModel::onIntent
    )
}

@Composable
fun NoteScreenContent(
    noteState: NoteState,
    onIntent: (NoteIntent) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.padding(16.dp)) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noteState.title,
            onValueChange = { onIntent(NoteIntent.TitleChange(it)) },
            textStyle = MaterialTheme.typography.headlineSmall,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
            decorationBox = { innerTextField ->
                if (noteState.title.isEmpty())
                    Text(
                        text = stringResource(R.string.name),
                        style = MaterialTheme.typography.headlineSmall
                    )
                else
                    innerTextField()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        BasicTextField(
            value = noteState.text,
            onValueChange = { onIntent(NoteIntent.TextChange(it)) },
            textStyle = MaterialTheme.typography.bodyMedium,
            decorationBox = { innerTextField ->
                if (noteState.title.isEmpty())
                    Text(
                        text = stringResource(R.string.text),
                        style = MaterialTheme.typography.bodyMedium
                    )
                else
                    innerTextField()
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NoteScreenContent(
                noteState = NoteState(),
                onIntent = {},
            )
        }
    }
}