package com.example.flightsearch.ui.homeScreenHeader

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.flightsearch.R

@Composable
fun HomeScreenHeader(modifier: Modifier = Modifier) {
    var flightCodeFromUser by remember { mutableStateOf("") }

    TextField(
        value = flightCodeFromUser,
        onValueChange = { flightCodeFromUser = it },
        textStyle = MaterialTheme.typography.labelLarge,
        shape = MaterialTheme.shapes.large,
        placeholder = {
            Text(
                text = stringResource(R.string.search_bar_placeholder),
                style = MaterialTheme.typography.labelMedium
            )
        },
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Go
        ),
        trailingIcon = {
            if (flightCodeFromUser.isNotBlank()) {
                IconButton(
                    onClick = {
                        flightCodeFromUser = ""
                    },
                    modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.Clear,
                        contentDescription = stringResource(R.string.search_icon)
                    )
                }
            }
        },
        modifier = Modifier.fillMaxWidth()
    )
}