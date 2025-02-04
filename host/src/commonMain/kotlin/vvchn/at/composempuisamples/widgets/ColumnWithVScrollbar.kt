package vvchn.at.composempuisamples.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ScrollState
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
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.unit.Dp
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

    val measurePolicy = remember(scrollState) { vScrollMeasurePolicy(scrollState) }

    val radius = HostTheme.hostDimens.scrollBarCornerRadius

    val color by animateColorAsState(
        targetValue = if (isScrollVisible) HostTheme.hostColors.scrollBoxColor else Color.Transparent,
        animationSpec = if (isScrollVisible) TweenSpec(0) else TweenSpec(durationMillis = SCROLL_BOX_FADEOUT_DURATION)
    )

    LaunchedEffect(key1 = scrollState.isScrollInProgress, key2 = isHovered) {
        if (scrollState.isScrollInProgress || isHovered) {
            isScrollVisible = true
        } else {
            delay(SCROLL_BOX_FADEOUT_DELAY)
            isScrollVisible = false
        }
    }

    Layout(
        {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawRoundRect(
                            cornerRadius = CornerRadius(
                                radius.toPx(),
                                radius.toPx()
                            ),
                            color = color,
                        )
                    }
            )
        },
        modifier.hoverable(interactionSource),
        measurePolicy
    )
}

private fun vScrollMeasurePolicy(scrollState: ScrollState): MeasurePolicy {
    return MeasurePolicy { measurable, constraints ->
        val maxHeightFloat: Float
        val scrollbarHeight: Int
        val scrollbarOffset: Int
        val totalContentHeightFloat: Float

        if (scrollState.maxValue == 0) {
            scrollbarHeight = 0
            scrollbarOffset = 0
        }
        else {
            maxHeightFloat = constraints.maxHeight.toFloat()

            totalContentHeightFloat = scrollState.maxValue + maxHeightFloat

            scrollbarHeight =
                ((maxHeightFloat / totalContentHeightFloat) * maxHeightFloat).roundToInt()

            scrollbarOffset =
                ((scrollState.value / scrollState.maxValue.toFloat()) *
                        (maxHeightFloat - scrollbarHeight)).roundToInt()
        }


        val scrollBarConstraints = constraints.copy(minHeight = scrollbarHeight)
        val placeable = measurable.first().measure(scrollBarConstraints)

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable.placeRelativeWithLayer(x = 0, y = scrollbarOffset)
        }
    }
}