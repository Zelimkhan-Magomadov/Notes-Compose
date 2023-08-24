package zelimkhan.magomadov.notes.ui.note.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import zelimkhan.magomadov.notes.ui.theme.NotesTheme

@Composable
fun NoteTextField(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    textStyle: TextStyle = MaterialTheme.typography.labelMedium,
    placeholderText: String = "",
    placeholderStyle: TextStyle = MaterialTheme.typography.labelMedium,
    shape: Shape = RoundedCornerShape(8.dp),
    containerColor: Color = Color.Unspecified,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    paddings: PaddingValues = PaddingValues(),
    contentPaddings: PaddingValues = PaddingValues(8.dp),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default
) {
    BasicTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(paddings)
            .background(color = containerColor, shape = shape)
            .padding(contentPaddings)
            .clip(shape),
        value = value,
        onValueChange = onValueChange,
        textStyle = textStyle,
        keyboardOptions = keyboardOptions,
        keyboardActions = keyboardActions,
        decorationBox = { innerTextField ->
            Row(verticalAlignment = Alignment.CenterVertically) {
                leadingIcon?.run {
                    invoke()
                    Spacer(modifier = Modifier.width(6.dp))
                }
                Box(modifier = modifier.weight(1f)) {
                    if (value.isEmpty())
                        Text(text = placeholderText, style = placeholderStyle)
                    innerTextField()
                }
                trailingIcon?.invoke()
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    NotesTheme {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            var value by remember { mutableStateOf("") }
            NoteTextField(
                value = value,
                onValueChange = { value = it },
                textStyle = TextStyle(fontSize = 16.sp, color = Color.Black),
                placeholderText = "Note",
                placeholderStyle = TextStyle(fontSize = 16.sp, color = Color.Gray),
                containerColor = Color.LightGray,
                keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Sentences)
            )
        }
    }
}