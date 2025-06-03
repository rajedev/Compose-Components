package com.rajedev.comp.component

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.rajedev.comp.R

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 27/05/25.
 * Description: This component displays expandable text.
 */

@Composable
fun ExpandableTextView(
    text: String,
    modifier: Modifier = Modifier,
    state: ExpandableState,
    moreLinkText: String = "more",
    lessLinkText: String = "less",
    maxLines: Int = 3,
    textStyle: TextStyle = MaterialTheme.typography.bodyLarge,
    linkColor: Color = MaterialTheme.colorScheme.primary
) {
    var isOverflowView by remember { mutableStateOf(false) }
    var textLayoutResult by remember { mutableStateOf<TextLayoutResult?>(null) }
    val linkAnnotation = "link"

    val annotatedString = remember(isOverflowView, state.isExpanded, text) {
        if (isOverflowView || state.isExpanded) {
            buildAnnotatedString {
                append(text)
                val suffix = if (!state.isExpanded) moreLinkText else lessLinkText
                append(" $suffix")
                addStyle(
                    style = SpanStyle(
                        color = linkColor, fontWeight = FontWeight.SemiBold,
                        textDecoration = TextDecoration.Underline
                    ),
                    start = this.length - suffix.length,
                    end = this.length
                )
                addStringAnnotation(
                    tag=linkAnnotation,
                    annotation = suffix,
                    start=this.length - suffix.length,
                    end = this.length
                )
            }
        } else {
            AnnotatedString(text)
        }
    }

    Column {
        Text(
            text = annotatedString,
            style = textStyle,
            maxLines = if (state.isExpanded) Int.MAX_VALUE else maxLines,
            overflow = TextOverflow.Ellipsis,
            onTextLayout = { result ->
                textLayoutResult = result
                isOverflowView = result.hasVisualOverflow
            },
            modifier = modifier.pointerInput(Unit) {
                detectTapGestures(onTap = { offset ->
                    textLayoutResult?.let {
                        val position = it.getOffsetForPosition(offset)
                        val annotation = annotatedString.getStringAnnotations(
                            tag = linkAnnotation,
                            position,
                            position
                        )
                        if (annotation.isNotEmpty()) {
                            state.toggle()
                        }
                    }
                })
            },
        )
    }
}

@Stable
class ExpandableState {
    var isExpanded by mutableStateOf(false)
        private set

    fun toggle() {
        isExpanded = !isExpanded
    }
}


@Composable
fun ExpandableText() {
    ExpandableTextView(
        text = "This is a very long tezzz ertss dfsadsfds f s".repeat(10),
        modifier = Modifier
            .fillMaxWidth()
            .padding(dimensionResource(R.dimen.padding_24)),
        state = remember { ExpandableState() },
        moreLinkText = "Read more"
    )
}

@Preview
@Composable
fun ExpandableTextPreview() {
    ExpandableText()
}
