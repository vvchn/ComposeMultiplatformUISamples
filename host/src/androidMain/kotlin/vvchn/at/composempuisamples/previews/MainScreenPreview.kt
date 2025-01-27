package vvchn.at.composempuisamples.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.screens.main.MainScreen
import vvchn.at.composempuisamples.widgets.absoluteHorizontalPadding

@CreatePreviews
@Composable
private fun MainScreenPreview() {
    HostPreview(isHeaderBtnVisible = false) {
        MainScreen(
            modifier = Modifier
                .fillMaxSize()
                .absoluteHorizontalPadding(it),
            actionHandler = {}
        )
    }
}