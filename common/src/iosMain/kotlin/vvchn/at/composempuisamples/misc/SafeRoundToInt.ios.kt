package vvchn.at.composempuisamples.misc

import kotlin.math.roundToInt

// JVM-like behavior
actual inline fun Float.safeRoundToInt(): Int = if (isNaN()) 0 else roundToInt()
