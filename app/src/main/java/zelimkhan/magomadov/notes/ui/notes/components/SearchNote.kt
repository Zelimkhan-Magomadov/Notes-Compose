package zelimkhan.magomadov.notes.ui.notes.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import zelimkhan.magomadov.notes.R
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

@Composable
fun SearchNote(
    value: String,
    onValueChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(32.dp),
        leadingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = null) },
        placeholder = { Text(text = stringResource(R.string.search_notes)) },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
        keyboardActions = KeyboardActions(onSearch = { onSearch() }),
        colors = TextFieldDefaults.colors(
            unfocusedIndicatorColor = Color.Unspecified,
            focusedIndicatorColor = Color.Unspecified,
            disabledIndicatorColor = Color.Unspecified,
            errorIndicatorColor = Color.Unspecified,
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        Box(modifier = Modifier.padding(32.dp)) {
            SearchNote(
                value = "",
                onValueChange = {},
                onSearch = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}