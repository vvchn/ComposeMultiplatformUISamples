package vvchn.at.composempuisamples.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.host.generated.resources.app_name
import vvchn.at.composempuisamples.theme.HostTheme

@Composable
internal fun HostTopBar(
    modifier: Modifier = Modifier,
    isBtnVisible: Boolean,
    onTextWidthChanged: (Int) -> Unit = {},
    onBtnClicked: () -> Unit = {}
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isBtnVisible) Arrangement.Start else Arrangement.Center
    ) {
        if (isBtnVisible) {
            Spacer(Modifier.width(HostTheme.hostDimens.moveBackIconStartPadding))
            IconButton(onClick = onBtnClicked) {
                Icon(
                    contentDescription = null,
                    imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    modifier = Modifier.size(HostTheme.hostDimens.moveBackIconSize)
                )
            }
        }
        else {
            Text(
                modifier = Modifier.onSizeChanged { onTextWidthChanged(it.width) },
                textAlign = TextAlign.Center,
                style = HostTheme.hostTypography.h2,
                text = stringResource(Res.string.app_name)
            )
        }
    }
}