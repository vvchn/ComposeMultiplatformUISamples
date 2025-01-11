package vvchn.at.composempuisamples.widgets

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import org.jetbrains.compose.resources.stringResource
import vvchn.at.composempuisamples.host.generated.resources.Res
import vvchn.at.composempuisamples.host.generated.resources.app_name
import vvchn.at.composempuisamples.theme.HostTheme

@Composable
internal fun HostTopBar(
    isBtnVisible: Boolean,
    onBtnClicked: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(min = HostTheme.hostDimens.headerHeight),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = if (isBtnVisible) Arrangement.Start else Arrangement.Center
    ) {
        if (isBtnVisible) {
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
                textAlign = TextAlign.Center,
                style = HostTheme.hostTypography.h2,
                text = stringResource(Res.string.app_name)
            )
        }
    }
}