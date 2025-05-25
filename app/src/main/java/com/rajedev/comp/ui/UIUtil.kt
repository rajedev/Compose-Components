package com.rajedev.comp.ui

import androidx.annotation.DimenRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 25/05/25.
 * Description: This file will be used to provide utility functions for UI components.
 */

@Composable
fun fontDimensionResource(
    @DimenRes id: Int
): TextUnit = dimensionResource(id = id).value.sp
