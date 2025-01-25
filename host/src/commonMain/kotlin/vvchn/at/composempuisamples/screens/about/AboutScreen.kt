package vvchn.at.composempuisamples.screens.about

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.host.generated.resources.app_name_short
import vvchn.at.composempuisamples.host.generated.resources.at_vvchn
import vvchn.at.composempuisamples.host.generated.resources.licenses
import vvchn.at.composempuisamples.host.generated.resources.version
import vvchn.at.composempuisamples.misc.Environment
import vvchn.at.composempuisamples.theme.HostTheme
import vvchn.at.composempuisamples.widgets.columnArrangement

@Composable
internal fun AboutScreenRoot(
    modifier: Modifier = Modifier,
    component: AboutScreenComponent
) {
    AboutScreen(
        modifier,
        component.environment,
        component.urls
    )
}

@Composable
internal fun AboutScreen(modifier: Modifier, environment: Environment, urls: Urls) {
    val uriHandler = LocalUriHandler.current
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        AppInfoElement(environment)
        Ownership(
            openGithub = { uriHandler.openUri(urls.githubLink) },
            openLicense = { uriHandler.openUri(urls.licenseLink) }
        )
    }
}

@Composable
private fun AppInfoElement(environment: Environment) {
    Column(
        verticalArrangement = Arrangement.columnArrangement(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = HostTheme.hostTypography.h1,
            text = stringResource(Res.string.app_name_short)
        )
        Text(
            textAlign = TextAlign.Center,
            style = HostTheme.hostTypography.h4,
            text = environment.systemInfo
        )
        if (environment.sdkInfo != null) {
            Text(
                textAlign = TextAlign.Center,
                style = HostTheme.hostTypography.h4,
                text = environment.sdkInfo!!
            )
        }
        Text(
            style = HostTheme.hostTypography.h5,
            text = "${stringResource(Res.string.version)} ${environment.appVersion}"
        )
    }
}

@Composable
private fun Ownership(openGithub: () -> Unit, openLicense: () -> Unit) {
    Column(
        modifier = Modifier.width(IntrinsicSize.Max),
        verticalArrangement = Arrangement.columnArrangement(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        FilledTonalButton(modifier = Modifier.fillMaxWidth(), onClick = openGithub) {
            Text(
                style = HostTheme.hostTypography.h3SemiBold,
                text = stringResource(Res.string.at_vvchn)
            )
        }
        Button(onClick = openLicense) {
            Text(
                style = HostTheme.hostTypography.h3SemiBold,
                text = stringResource(Res.string.licenses)
            )
        }
    }
}