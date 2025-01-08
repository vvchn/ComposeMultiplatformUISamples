package vvchn.at.composempuisamples.screens.misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import vvchn.at.composempuisamples.theme.HostTheme

@Composable
internal inline fun Arrangement.columnArrangement() = spacedBy(
    HostTheme.hostDimens.mainScreenColumnItemVerticalPadding,
    Alignment.CenterVertically
)