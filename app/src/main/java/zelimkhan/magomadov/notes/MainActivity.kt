package zelimkhan.magomadov.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import zelimkhan.magomadov.notes.ui.notes.NotesScreen
import zelimkhan.magomadov.notes.ui.notes.NotesViewModel
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val notesViewModel: NotesViewModel by viewModels()
                    val notes = notesViewModel.notes.collectAsState().value
                    val noteCategoryState = notesViewModel.noteCategoryState.collectAsState().value
                    NotesScreen(
                        notes = notes,
                        noteCategoryState = noteCategoryState,
                        onIntent = notesViewModel::onIntent
                    )
                }
            }
        }
    }
}