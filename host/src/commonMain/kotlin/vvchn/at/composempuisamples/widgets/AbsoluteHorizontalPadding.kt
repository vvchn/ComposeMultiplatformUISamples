package vvchn.at.composempuisamples.widgets

import androidx.annotation.IntRange
import androidx.compose.runtime.Stable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.Measurable
import androidx.compose.ui.layout.MeasureResult
import androidx.compose.ui.layout.MeasureScope
import androidx.compose.ui.node.LayoutModifierNode
import androidx.compose.ui.node.ModifierNodeElement
import androidx.compose.ui.unit.Constraints

@Stable
fun Modifier.absoluteHorizontalPadding(@IntRange(from = 0) delta: Int): Modifier =
    this then AbsoluteHorizontalPaddingElement(delta)

private class AbsoluteHorizontalPaddingElement(
    private val delta: Int
) : ModifierNodeElement<AbsoluteHorizontalPaddingNode>() {

    init {
        require(delta >= 0) { "Delta must be non-negative" }
    }

    override fun create(): AbsoluteHorizontalPaddingNode = AbsoluteHorizontalPaddingNode(delta)

    override fun update(node: AbsoluteHorizontalPaddingNode) {
        node.delta = delta
    }

    override fun hashCode(): Int = delta.hashCode() * 42

    override fun equals(other: Any?): Boolean =
        other is AbsoluteHorizontalPaddingElement && other.delta == this.delta
}

private class AbsoluteHorizontalPaddingNode(
    var delta: Int
) : LayoutModifierNode, Modifier.Node() {

    override fun MeasureScope.measure(
        measurable: Measurable,
        constraints: Constraints
    ): MeasureResult {
        val containerWidth = constraints.maxWidth

        val appliedPadding = if (delta >= containerWidth) 0 else (containerWidth - delta) / 2

        val newConstraints = constraints.copy(
            minWidth = 0,
            maxWidth = containerWidth - appliedPadding * 2
        )

        val placeable = measurable.measure(newConstraints)

        val width = placeable.width + appliedPadding * 2
        val height = placeable.height

        return layout(width, height) { placeable.place(appliedPadding, 0) }
    }
}