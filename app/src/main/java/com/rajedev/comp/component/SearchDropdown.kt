package com.rajedev.comp.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.rajedev.comp.ui.launch.Screens

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 24/05/25.
 * Description:
 */

@Composable
fun SearchDropDownScreen(navigateTo: (Screens) -> Unit) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = "Search Dropdown Component")
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchDropDownPreview() {
    SearchDropDownScreen(navigateTo = {})
}
