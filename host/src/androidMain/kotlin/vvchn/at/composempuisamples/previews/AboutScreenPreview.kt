package vvchn.at.composempuisamples.previews

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import vvchn.at.composempuisamples.misc.getEnvironment
import vvchn.at.composempuisamples.screens.about.AboutScreen
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.absoluteHorizontalPadding

@CreatePreviews
@Composable
private fun AboutScreenPreview(modifier: Modifier = Modifier) {
    HostPreview {
        AboutScreen(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = HostTheme.hostDimens.aboutScreenTopPadding,
                    bottom = HostTheme.hostDimens.aboutScreenBottomPadding
                ).absoluteHorizontalPadding(it),
            environment = getEnvironment(),
            openGithub = {},
            showLicense = {}
        )
    }
}