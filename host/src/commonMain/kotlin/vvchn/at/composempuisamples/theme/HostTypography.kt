package vvchn.at.composempuisamples.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.Stable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

@Immutable
internal data class HostTypography(
    val h1: TextStyle = hostTextStyle(fontSize = 28.sp, fontWeight = FontWeight.Bold),
    val h2: TextStyle = hostTextStyle(fontSize = 20.sp, fontWeight = FontWeight.Bold),
    val h2SemiBold: TextStyle = h2.copy(fontWeight = FontWeight.SemiBold),
    val h3: TextStyle = hostTextStyle(fontSize = 18.sp),
    val h3Bold: TextStyle = h3.copy(fontWeight = FontWeight.Bold),
    val h3SemiBold: TextStyle = h3.copy(fontWeight = FontWeight.SemiBold),
    val h4: TextStyle = hostTextStyle(fontSize = 16.sp, fontWeight = FontWeight.SemiBold),
)

@Stable
private fun hostTextStyle(
    fontFamily: FontFamily = FontFamily.Default,
    fontWeight: FontWeight = FontWeight.Normal,
    fontSize: TextUnit = TextUnit.Unspecified,
    lineHeight: TextUnit = TextUnit.Unspecified
): TextStyle {
    return TextStyle(
        fontFamily = fontFamily,
        fontWeight = fontWeight,
        fontSize = fontSize,
        lineHeight = lineHeight
    )
}