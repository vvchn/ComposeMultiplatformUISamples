package vvchn.at.composempuisamples.previews

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.screens.main.MainScreen
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar
import vvchn.at.composempuisamples.widgets.absoluteHorizontalPadding

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@CreatePreviews
@Composable
fun MainScreenPreview() {
    var headerTextWidth by remember { mutableIntStateOf(0) }

    HostTheme {
        Scaffold(
            topBar = {
                HostTopBar(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(HostTheme.hostDimens.headerHeight),
                    onTextWidthChanged = { headerTextWidth = it },
                    isBtnVisible = false
                )
            }
        ) {
            MainScreen(
                modifier = Modifier
                    .fillMaxSize()
                    .absoluteHorizontalPadding(headerTextWidth),
                actionHandler = {}
            )
        }
    }
}