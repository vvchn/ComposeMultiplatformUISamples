package vvchn.at.composempuisamples.previews

import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

/** [Reference:](https://developer.android.com/develop/ui/compose/tooling/previews#preview-multipreview) */
// Previews in AS works only for android target
@Target(AnnotationTarget.FUNCTION)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES, locale = "ru")
annotation class CreatePreviews