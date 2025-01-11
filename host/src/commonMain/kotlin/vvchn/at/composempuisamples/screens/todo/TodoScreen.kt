package vvchn.at.composempuisamples.screens.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.host.generated.resources.endComment
import vvchn.at.composempuisamples.host.generated.resources.startComment
import vvchn.at.composempuisamples.theme.HostTheme

@Composable
internal fun TodoScreenRoot(component: TodoScreenComponent) {
    TodoScreen(component.text)
}

@Composable
internal fun TodoScreen(text: String) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.surface
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                textAlign = TextAlign.Center,
                style = HostTheme.hostTypography.h1,
                color = HostTheme.hostColors.todo,
                text = stringResource(Res.string.startComment)
            )
            Spacer(Modifier.height(HostTheme.hostDimens.todoScreenSpacer))
            Text(
                textAlign = TextAlign.Center,
                style = HostTheme.hostTypography.h3Bold,
                text = text + stringResource(Res.string.endComment)
            )
        }
    }
}