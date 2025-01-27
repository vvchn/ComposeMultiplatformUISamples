package vvchn.at.composempuisamples.previews

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@Composable
internal fun HostPreview(
    isHeaderBtnVisible: Boolean = true,
    content: @Composable (Int) -> Unit
) {
    var headerTextWidth by remember { mutableIntStateOf(900) }

    HostTheme {
        Scaffold(
            topBar = {
                HostTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HostTheme.hostDimens.headerHeight),
                    onTextWidthChanged = { headerTextWidth = it },
                    isBtnVisible = isHeaderBtnVisible
                )
            }
        ) {
            Box(Modifier.fillMaxSize().padding(it)) {
                content(headerTextWidth)
            }
        }
    }
}