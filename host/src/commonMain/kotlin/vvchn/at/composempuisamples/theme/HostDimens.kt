package vvchn.at.composempuisamples.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
internal data class HostDimens(
    /* Paddings */
    val columnHorizontalPadding: Dp = 60.dp,
    val mainScreenColumnItemVerticalPadding: Dp = 18.dp,

    /* Dimensions */
    val mainScreenHeaderHeight: Dp = 60.dp,
    val sampleItemHeight: Dp = 80.dp,
    val sampleItemShadowElevation: Dp = 4.dp,
    val sampleItemTonalElevation: Dp = 2.dp,
    val todoScreenSpacer: Dp = 10.dp,
)