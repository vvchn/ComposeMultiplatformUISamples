package vvchn.at.composempuisamples.screens.about.license

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.ExperimentalResourceApi
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.misc.Environment
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.ColumnWithVScrollbar

@Composable
internal fun LicenseScreenRoot(
    modifier: Modifier = Modifier,
    component: LicenseScreenComponent
) {
    LicenseScreen(modifier, component.pathToLicense, component.environment)
}

@Composable
internal fun LicenseScreen(
    modifier: Modifier = Modifier,
    pathToLicense: String,
    environment: Environment
) {
    ColumnWithVScrollbar(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LicenseElement(pathToLicense, environment.isDesktop)
    }
}

@OptIn(ExperimentalResourceApi::class)
@Composable
internal fun LicenseElement(pathToLicense: String, isDesktop: Boolean) {
    var bytes by remember {
        mutableStateOf(ByteArray(0))
    }

    LaunchedEffect(Unit) {
        bytes = Res.readBytes(pathToLicense)
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = HostTheme.hostColors.licenseBackground
    ) {
        SelectionContainer {
            Text(
                style = if (isDesktop) HostTheme.hostTypography.h5 else HostTheme.hostTypography.h6,
                text = bytes.decodeToString()
            )
        }
    }
}