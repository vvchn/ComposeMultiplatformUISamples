package vvchn.at.composempuisamples.previews

import androidx.compose.runtime.Composable
import vvchn.at.composempuisamples.screens.todo.TodoScreen
import vvchn.at.composempuisamples.theme.HostTheme

@CreatePreviews
@Composable
fun TodoScreenPreview() {
    HostTheme {
        TodoScreen("Preview")
    }
}