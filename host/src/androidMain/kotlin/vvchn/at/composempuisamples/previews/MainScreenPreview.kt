package vvchn.at.composempuisamples.previews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.screens.main.MainScreen
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@CreatePreviews
@Composable
fun MainScreenPreview() {
    HostTheme {
        Scaffold(
            topBar = {
                HostTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(min = HostTheme.hostDimens.headerHeight),
                    isBtnVisible = true
                )
            }
        ) {
            MainScreen { }
        }
    }
}