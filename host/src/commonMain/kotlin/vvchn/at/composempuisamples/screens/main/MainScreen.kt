package vvchn.at.composempuisamples.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.host.generated.resources.about
import vvchn.at.composempuisamples.host.generated.resources.app_name
import vvchn.at.composempuisamples.host.generated.resources.sample
import vvchn.at.composempuisamples.screens.misc.columnArrangement
import vvchn.at.composempuisamples.theme.HostTheme

@Composable
internal fun MainScreen(component: MainScreenComponent) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Box(modifier = Modifier.fillMaxSize().systemBarsPadding()) {
            Header(Modifier.fillMaxWidth().height(HostTheme.hostDimens.mainScreenHeaderHeight))
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = HostTheme.hostDimens.columnHorizontalPadding,
                        vertical = HostTheme.hostDimens.mainScreenHeaderHeight),
                verticalArrangement = Arrangement.columnArrangement(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ScreenSampleItem(stringResource(Res.string.sample, 1)) { }
                ScreenSampleItem(stringResource(Res.string.sample, 2)) { }
                ScreenSampleItem(stringResource(Res.string.about)) {}
            }
        }
    }
}

@Composable
private fun Header(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            textAlign = TextAlign.Center,
            style = HostTheme.hostTypography.h2,
            text = stringResource(Res.string.app_name)
        )
    }
}

@Composable
private fun ScreenSampleItem(
    text: String,
    onClick: () -> Unit
) {
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(HostTheme.hostDimens.sampleItemHeight)
            .clickable { onClick() },
        shadowElevation = HostTheme.hostDimens.sampleItemShadowElevation,
        tonalElevation = HostTheme.hostDimens.sampleItemTonalElevation,
        shape = HostTheme.hostShapes.sampleItemShape
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                textAlign = TextAlign.Center,
                style = HostTheme.hostTypography.h4,
                text = text
            )
        }
    }
}