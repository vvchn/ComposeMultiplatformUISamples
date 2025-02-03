package vvchn.at.composempuisamples.widgets

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.background
import androidx.compose.foundation.hoverable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsHoveredAsState
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import vvchn.at.composempuisamples.theme.HostTheme
import kotlin.math.roundToInt

const val SCROLL_BOX_FADEOUT_DELAY = 1000L
const val SCROLL_BOX_FADEOUT_DURATION = 1000

@Composable
internal fun ColumnWithVScrollbar(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    scrollState: ScrollState = rememberScrollState(),
    scrollbarWidth: Dp = HostTheme.hostDimens.scrollBarWidth,
    scrollBarOffset: Dp = HostTheme.hostDimens.scrollBarOffset,
    interactionSource: MutableInteractionSource = remember { MutableInteractionSource() },
    content: @Composable (ColumnScope.() -> Unit)
) {
    ColumnWithVScrollbarLayout(
        modifier = modifier,
        scrollBarOffset = scrollBarOffset,
        column = {
            Column(
                modifier = Modifier.verticalScroll(scrollState),
                verticalArrangement = verticalArrangement,
                horizontalAlignment = horizontalAlignment,
                content = content
            )
        },
        scrollBar = {
            DefaultVScrollBar(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(scrollbarWidth),
                scrollState = scrollState,
                interactionSource = interactionSource
            )
        }
    )
}

@Composable
private fun ColumnWithVScrollbarLayout(
    modifier: Modifier = Modifier,
    scrollBarOffset: Dp,
    column: @Composable () -> Unit,
    scrollBar: @Composable () -> Unit
) {
    Layout(
        modifier = modifier,
        contents = listOf(column, scrollBar)
    ) { (columnMeasurable, scrollBarMeasurable), constraints ->
        val absoluteConstraints = constraints.copy(minWidth = 0, minHeight = 0)

        val scrollBarPlaceable = scrollBarMeasurable.first().measure(absoluteConstraints)
        val scrollBarOffsetPx = scrollBarPlaceable.width + scrollBarOffset.roundToPx()

        val columnPlaceable = columnMeasurable.first().measure(absoluteConstraints)

        layout(columnPlaceable.width, columnPlaceable.height) {
            columnPlaceable.placeRelative(0, 0)
            scrollBarPlaceable.placeRelative(columnPlaceable.width - scrollBarOffsetPx, 0)
        }
    }
}

@Composable
private fun DefaultVScrollBar(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    interactionSource: MutableInteractionSource
) {
    var isScrollVisible by remember { mutableStateOf(false) }
    val isHovered by interactionSource.collectIsHoveredAsState()

    LaunchedEffect(key1 = scrollState.isScrollInProgress, key2 = isHovered) {
        if (scrollState.isScrollInProgress || isHovered) {
            isScrollVisible = true
        } else {
            delay(SCROLL_BOX_FADEOUT_DELAY)
            isScrollVisible = false
        }
    }

    Box(modifier.hoverable(interactionSource)) {
        AnimatedVisibility(
            modifier = modifier,
            visible = isScrollVisible || isHovered,
            enter = EnterTransition.None,
            exit = fadeOut(animationSpec = tween(SCROLL_BOX_FADEOUT_DURATION))
        ) {
            Layout(
                content = {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.Gray, shape = RoundedCornerShape(4.dp))
                    )
                }
            ) { measurable, constraints ->
                val maxHeightFloat = constraints.maxHeight.toFloat()
                val totalContentHeightPx = scrollState.maxValue + maxHeightFloat

                val scrollbarHeight =
                    ((maxHeightFloat / totalContentHeightPx) * maxHeightFloat).roundToInt()
                val scrollbarOffsetPx =
                    ((scrollState.value.toFloat() / scrollState.maxValue) * (maxHeightFloat - scrollbarHeight)).roundToInt()

                val scrollBarConstraints = constraints.copy(minHeight = scrollbarHeight)
                val placeable = measurable.first().measure(scrollBarConstraints)
                layout(constraints.maxWidth, constraints.maxHeight) {
                    placeable.placeRelative(x = 0, y = scrollbarOffsetPx)
                }
            }
        }
    }
}
