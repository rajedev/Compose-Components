package com.rajedev.comp.component

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 27/05/25.
 * Description: This component displays expandable text.
 */

@Composable
fun ExpandableText() {
    Column {
        Text(
            text = "Expandable Text Component"
        )
    }
}

@Preview
@Composable
fun ExpandableTextPreview() {
    ExpandableText()
}
