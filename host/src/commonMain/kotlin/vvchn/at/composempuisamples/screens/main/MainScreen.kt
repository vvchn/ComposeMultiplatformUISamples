package vvchn.at.composempuisamples.screens.main

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.stringResource
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.host.generated.resources.about
import vvchn.at.composempuisamples.host.generated.resources.sample
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.columnArrangement

@Composable
internal fun MainScreenRoot(
    modifier: Modifier = Modifier,
    component: MainScreenComponent
) {
    MainScreen(modifier, component::actionHandler)
}

@Composable
internal fun MainScreen(
    modifier: Modifier,
    actionHandler: (MainScreenAction) -> Unit
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.columnArrangement(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ScreenSampleItem(stringResource(Res.string.sample, 1)) {
            actionHandler(MainScreenAction.OpenSample1)
        }
        ScreenSampleItem(stringResource(Res.string.sample, 2)) {
            actionHandler(MainScreenAction.OpenSample2)
        }
        ScreenSampleItem(stringResource(Res.string.about)) {
            actionHandler(MainScreenAction.OpenAboutPage)
        }
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
        Box(contentAlignment = Alignment.Center) {
            Text(
                style = HostTheme.hostTypography.h4,
                text = text
            )
        }
    }
}
