package vvchn.at.composempuisamples.previews

import androidx.compose.runtime.Composable
import vvchn.at.composempuisamples.screens.main.MainScreen
import vvchn.at.composempuisamples.theme.HostTheme

@CreatePreviews
@Composable
fun MainScreenPreview() {
    HostTheme {
        MainScreen { }
    }
}