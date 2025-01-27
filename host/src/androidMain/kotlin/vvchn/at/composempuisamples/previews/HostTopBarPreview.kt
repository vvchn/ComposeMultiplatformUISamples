package vvchn.at.composempuisamples.previews

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@CreatePreviews
@Composable
private fun HostTopBarBtnPreview() {
    HostTheme {
        Surface {
            HostTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = HostTheme.hostDimens.headerHeight),
                isBtnVisible = true
            )
        }
    }
}

@CreatePreviews
@Composable
private fun HostTopBarTextPreview() {
    HostTheme {
        Surface {
            HostTopBar(
                modifier = Modifier
                    .fillMaxWidth()
                    .heightIn(min = HostTheme.hostDimens.headerHeight),
                isBtnVisible = false
            )
        }
    }
}
