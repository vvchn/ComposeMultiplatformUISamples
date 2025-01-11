package vvchn.at.composempuisamples.previews

import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@CreatePreviews
@Composable
fun HostTopBarPreview() {
    HostTheme {
        Surface {
            HostTopBar(true) { }
        }
    }
}