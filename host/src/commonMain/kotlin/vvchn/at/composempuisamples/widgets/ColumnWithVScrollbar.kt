package vvchn.at.composempuisamples.widgets

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.scrollBy
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
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.structuralEqualityPolicy
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.MeasurePolicy
import androidx.compose.ui.unit.Dp
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import vvchn.at.composempuisamples.misc.safeRoundToInt
import vvchn.at.composempuisamples.theme.HostTheme

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

// TODO: Clickable Scroll Bar
@Composable
private fun DefaultVScrollBar(
    modifier: Modifier = Modifier,
    scrollState: ScrollState,
    interactionSource: MutableInteractionSource
) {
    var isScrollVisible by remember { mutableStateOf(false) }

    val isScrollInProgress by remember {
        derivedStateOf(policy = structuralEqualityPolicy()) {
            scrollState.isScrollInProgress
        }
    }

    val isHovered by interactionSource.collectIsHoveredAsState()

    val dragScope = rememberCoroutineScope()

    val dragMutex by remember { mutableStateOf(Mutex()) }

    var isScrollThumbBeingDragged by remember { mutableStateOf(false) }

    var scrollLayoutHeight by remember { mutableStateOf(0) }

    val radius = HostTheme.hostDimens.scrollBarCornerRadius

    val color by animateColorAsState(
        targetValue = if (isScrollVisible) {
            HostTheme.hostColors.scrollBoxColor
        } else {
            Color.Transparent
        },
        animationSpec = if (isScrollVisible) {
            TweenSpec(0)
        } else {
            TweenSpec(durationMillis = SCROLL_BOX_FADEOUT_DURATION)
        }
    )

    val measurePolicy = remember { vScrollMeasurePolicy({ scrollLayoutHeight = it }, scrollState) }

    LaunchedEffect(
        key1 = isScrollInProgress,
        key2 = isHovered,
        key3 = isScrollThumbBeingDragged
    ) {
        if (isScrollInProgress || isHovered || isScrollThumbBeingDragged) {
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
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDragStart = { isScrollThumbBeingDragged = true },
                            onDrag = { change, dragAmount ->
                                dragScope.launch(start = CoroutineStart.UNDISPATCHED) {
                                    dragMutex.withLock {
                                        isScrollVisible = true
                                        scrollState.scrollBy(
                                            dragAmount.y * (scrollState.maxValue / scrollLayoutHeight)
                                        )
                                    }
                                }
                                change.consume()
                            },
                            onDragEnd = { isScrollThumbBeingDragged = false },
                            onDragCancel = { isScrollThumbBeingDragged = false }
                        )
                    }
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

private fun vScrollMeasurePolicy(
    onScreenHeightChanged: (Int) -> Unit,
    scrollState: ScrollState
): MeasurePolicy {
    return MeasurePolicy { measurable, constraints ->
        onScreenHeightChanged(constraints.maxHeight)
        val maxHeightFloat = constraints.maxHeight.toFloat()
        val totalContentHeightFloat = scrollState.maxValue + maxHeightFloat

        val scrollbarHeight =
            ((maxHeightFloat / totalContentHeightFloat) * maxHeightFloat).safeRoundToInt()

        val scrollbarOffset = ((scrollState.value / scrollState.maxValue.toFloat()) *
                (maxHeightFloat - scrollbarHeight)).safeRoundToInt()

        val scrollBarConstraints = constraints.copy(minHeight = scrollbarHeight)

        val placeable = measurable.first().measure(scrollBarConstraints)

        layout(constraints.maxWidth, constraints.maxHeight) {
            placeable.placeRelativeWithLayer(x = 0, y = scrollbarOffset)
        }
    }
}
