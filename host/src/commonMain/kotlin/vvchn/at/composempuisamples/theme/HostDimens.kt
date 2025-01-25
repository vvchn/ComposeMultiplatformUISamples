package vvchn.at.composempuisamples.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
internal data class HostDimens(
    /* Paddings */
    val aboutScreenBottomPadding: Dp = 60.dp,
    val aboutScreenTopPadding: Dp = 20.dp,
    val columnItemVerticalPadding: Dp = 18.dp,
    val moveBackIconStartPadding: Dp = 10.dp,

    /* Dimensions */
    val headerHeight: Dp = 60.dp,
    val sampleItemHeight: Dp = 80.dp,
    val sampleItemShadowElevation: Dp = 4.dp,
    val sampleItemTonalElevation: Dp = 2.dp,
    val todoScreenSpacer: Dp = 10.dp,
    val moveBackIconSize: Dp = 30.dp,
)