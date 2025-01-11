package vvchn.at.composempuisamples.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import vvchn.at.composempuisamples.theme.HostTheme

@Suppress("NOTHING_TO_INLINE", "KotlinRedundantDiagnosticSuppress")
@Composable
internal inline fun Arrangement.columnArrangement() = spacedBy(
    HostTheme.hostDimens.mainScreenColumnItemVerticalPadding,
    Alignment.CenterVertically
)