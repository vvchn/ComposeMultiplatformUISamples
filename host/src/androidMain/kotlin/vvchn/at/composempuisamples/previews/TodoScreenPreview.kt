package vvchn.at.composempuisamples.previews

import androidx.compose.runtime.Composable
import vvchn.at.composempuisamples.screens.todo.TodoScreen

@CreatePreviews
@Composable
private fun TodoScreenPreview() {
    HostPreview {
        TodoScreen("Preview")
    }
}