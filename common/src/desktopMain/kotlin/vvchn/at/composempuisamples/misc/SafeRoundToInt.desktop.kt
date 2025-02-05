package vvchn.at.composempuisamples.misc

import androidx.compose.ui.util.fastRoundToInt

actual inline fun Float.safeRoundToInt(): Int = this.fastRoundToInt()
