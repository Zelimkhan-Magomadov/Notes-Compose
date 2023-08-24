package zelimkhan.magomadov.notes.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable

@Composable
fun NotesTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme)
        DarkColors
    else
        LightColors

    MaterialTheme(
        colorScheme = colors,
        content = content,
        typography = Typography
    )
}