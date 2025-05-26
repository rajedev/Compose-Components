package com.rajedev.comp.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Clear
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import com.rajedev.comp.R
import com.rajedev.comp.getCountries

/**
 * Copyright (c) 2025 /LiveRamp, All rights reserved.
 * Created by Rajendhiran Easu on 24/05/25.
 * Description:
 */

@Composable
fun AutoCompleteSearch(
    suggestionList: List<String> = getCountries()
) {
    var inputQuery by remember {
        mutableStateOf(
            TextFieldValue(
                text = "",
                selection = TextRange("".length)
            )
        )
    }
    var hasDropdownVisible by remember { mutableStateOf(false) }
    var filteredSuggestions =
        suggestionList.filter {
            it.contains(
                inputQuery.text,
                ignoreCase = true
            ) && it != inputQuery.text
        }

    val hasClearButtonVisible by remember {
        derivedStateOf {
            inputQuery.text.isNotBlank()
        }
    }

    AutoCompleteSearchTextView(
        modifier = Modifier,
        inputQuery = inputQuery,
        hasDropdownVisible = hasDropdownVisible,
        hasClearButtonVisible = hasClearButtonVisible,
        filteredSuggestions = filteredSuggestions,
        onInputChange = { newValue ->
            inputQuery = newValue.copy(selection = TextRange(newValue.text.length))
            hasDropdownVisible = inputQuery.text.isNotEmpty() && filteredSuggestions.isNotEmpty()
        },
        onSuggestionClick = { suggestion ->
            inputQuery = TextFieldValue(text = suggestion, selection = TextRange(suggestion.length))
            hasDropdownVisible = false
        },
        onClear = {
            inputQuery = TextFieldValue(text = "", selection = TextRange(0))
        },
        onDismissRequest = {
            hasDropdownVisible = false
        }
    )

}

@Composable
private fun AutoCompleteSearchTextView(
    modifier: Modifier,
    inputQuery: TextFieldValue,
    hasClearButtonVisible: Boolean,
    hasDropdownVisible: Boolean,
    filteredSuggestions: List<String>,
    onInputChange: (TextFieldValue) -> Unit,
    onSuggestionClick: (String) -> Unit,
    onClear: () -> Unit,
    onDismissRequest: () -> Unit,
) {
    Box(modifier = Modifier.fillMaxWidth()) {
        OutlinedTextField(
            value = inputQuery,
            modifier = modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .padding(dimensionResource(R.dimen.padding_16)),
            onValueChange = onInputChange,
            singleLine = true,
            label = {
                Text(text = stringResource(R.string.search_label))
            },
            placeholder = {
                Text(text = stringResource(R.string.search_placeholder_label))
            },
            trailingIcon = {
                if (hasClearButtonVisible) {
                    IconButton(
                        onClick = onClear,
                        enabled = inputQuery.text.isNotBlank()
                    ) {
                        Icon(
                            imageVector = Icons.Rounded.Clear,
                            contentDescription = stringResource(R.string.search_clear_icon_desc)
                        )
                    }
                }
            },
            leadingIcon = {
                Icon(
                    imageVector = Icons.Rounded.Search,
                    contentDescription = stringResource(R.string.search_placeholder_label)
                )
            },
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
        )

        DropdownMenu(
            modifier = Modifier.fillMaxWidth(),
            expanded = hasDropdownVisible,
            onDismissRequest = onDismissRequest
        ) {
            filteredSuggestions.forEach { suggestion ->
                DropdownMenuItem(
                    onClick = {
                        onSuggestionClick(suggestion)
                    }, modifier = Modifier.fillMaxWidth(),
                    text = {
                        Text(
                            text = suggestion,
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(dimensionResource(R.dimen.padding_16))
                        )
                    })
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun SearchDropDownPreview() {
    Column(modifier = Modifier.padding(top = dimensionResource(R.dimen.top_space))) {
        AutoCompleteSearch()
    }
}
