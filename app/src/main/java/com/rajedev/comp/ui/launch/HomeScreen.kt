package com.rajedev.comp.ui.launch

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.rajedev.comp.R
import com.rajedev.comp.ui.fontDimensionResource

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 24/05/25.
 * Description: Home Screen that displays a list of components.
 */

@Composable
fun HomeScreen(navigateTo: (Screens) -> Unit) {
    val components: List<Component> = reusableComponents()
    LazyColumn {
        items(components, key = { comp -> comp.cId }) { component ->
            ComponentItem(component) {
                navigateTo(component.screens)
            }
        }
    }
}

@Composable
private fun ComponentItem(component: Component, onClick: () -> Unit) {
    val title = stringResource(component.screens.titleId)
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(dimensionResource(R.dimen.home_component_list_height))
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = component.componentResId,
            contentDescription = "Icon for $title",
            modifier = Modifier
                .wrapContentSize()
                .padding(dimensionResource(R.dimen.padding_16))
        )
        Text(
            text = title,
            modifier = Modifier
                .wrapContentHeight(align = Alignment.CenterVertically)
                .fillMaxWidth(.90f),
            style = LocalTextStyle.current.copy(
                fontSize = fontDimensionResource(R.dimen.text_size_18),
                color = Color.Black,
                fontWeight = FontWeight.Bold,
            )
        )
        Icon(
            imageVector = Icons.AutoMirrored.Filled.ArrowForward,
            modifier = Modifier.wrapContentSize(),
            contentDescription = "click to navigate to $title",
        )
    }
    HorizontalDivider(color = Color.Black, thickness = dimensionResource(R.dimen.divider_thickness))
}

@Preview(showSystemUi = true)
@Composable
private fun HomeScreenPreview() {
    HomeScreen(navigateTo = {})
}
