package zelimkhan.magomadov.notes.ui.note

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ramcosta.composedestinations.annotation.Destination
import zelimkhan.magomadov.notes.R
import zelimkhan.magomadov.notes.ui.note.components.NoteTextField
import zelimkhan.magomadov.notes.ui.note.state.NoteIntent
import zelimkhan.magomadov.notes.ui.note.state.NoteScreenNavArgs
import zelimkhan.magomadov.notes.ui.note.state.NoteState
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
        NoteTextField(
            modifier = Modifier.fillMaxWidth(),
            value = noteState.title,
            onValueChange = { onIntent(NoteIntent.TitleChange(it)) },
            textStyle = TextStyle(fontSize = 18.sp),
            placeholderText = stringResource(id = R.string.name),
            placeholderStyle = TextStyle(fontSize = 18.sp, color = Color.Gray),
            keyboardOptions = KeyboardOptions(
                capitalization = KeyboardCapitalization.Sentences,
                imeAction = ImeAction.Next
            )
        )

        Spacer(modifier = Modifier.height(16.dp))

        NoteTextField(
            modifier = Modifier.fillMaxSize(),
            value = noteState.text,
            onValueChange = { onIntent(NoteIntent.TextChange(it)) },
            textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
            placeholderText = stringResource(id = R.string.text),
            placeholderStyle = TextStyle(fontSize = 16.sp, color = Color.Gray),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    var title by remember { mutableStateOf("") }
    var text by remember { mutableStateOf("") }

    NotesTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            NoteScreenContent(
                noteState = NoteState(title = title, text = text),
                onIntent = {
                    when (it) {
                        is NoteIntent.TitleChange -> title = it.value
                        is NoteIntent.TextChange -> text = it.value
                    }
                }
            )
        }
    }
}