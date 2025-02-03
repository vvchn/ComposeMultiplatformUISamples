package vvchn.at.composempuisamples.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.datasource.LoremIpsum
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.ColumnWithVScrollbar

@CreatePreviews
@Composable
private fun ColumnWithScrollBarPreview(@PreviewParameter(LoremIpsum::class) text: String) {
    HostPreview {
        ColumnWithVScrollbar(
            modifier = Modifier.fillMaxSize(),
        ) {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = HostTheme.hostColors.licenseBackground
            ) {
                SelectionContainer {
                    Text(
                        style = HostTheme.hostTypography.h6,
                        text = text + text + text + text
                    )
                }
            }
        }
    }
}