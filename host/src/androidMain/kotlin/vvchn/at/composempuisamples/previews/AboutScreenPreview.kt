package vvchn.at.composempuisamples.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.misc.getEnvironment
import vvchn.at.composempuisamples.screens.about.AboutScreen
import vvchn.at.composempuisamples.screens.about.Urls
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.HostTopBar

@CreatePreviews
@Composable
fun AboutScreenPreview(modifier: Modifier = Modifier) {
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
            AboutScreen(
                Modifier
                    .fillMaxSize()
                    .padding(it)
                    .padding(
                        top = HostTheme.hostDimens.aboutScreenTopPadding,
                        bottom = HostTheme.hostDimens.aboutScreenBottomPadding
                    ),
                getEnvironment(),
                Urls("","")
            )
        }
    }
}