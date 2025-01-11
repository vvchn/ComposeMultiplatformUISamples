package vvchn.at.composempuisamples.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
internal data class HostDimens(
    /* Paddings */
    val horizontalPadding: Dp = 60.dp,
    val mainScreenColumnItemVerticalPadding: Dp = 18.dp,
    val moveBackIconStartPadding: Dp = 30.dp,

    /* Dimensions */
    val headerHeight: Dp = 80.dp,
    val sampleItemMaxWidth: Dp = 400.dp,
    val sampleItemMaxHeight: Dp = 80.dp,
    val sampleItemShadowElevation: Dp = 4.dp,
    val sampleItemTonalElevation: Dp = 2.dp,
    val todoScreenSpacer: Dp = 10.dp,
    val moveBackIconSize: Dp = 30.dp,
)