package com.example.flightsearch.ui.homeScreenHeader

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardActions
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
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.example.flightsearch.R
import com.example.flightsearch.data.db.entity.Flight

@Composable
fun HomeScreenHeader(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel
) {
    var flightCodeFromUser by remember { mutableStateOf("") }

    Column {
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

        Spacer(modifier = Modifier.height(dimensionResource(R.dimen.padding_regular)))

        if (flightCodeFromUser.isNotEmpty()) {
            SearchSuggestionList(flights = listOf(
                Flight(id = 1, iataCode = "CHE", name = "Chennai international Airport", passengers = 21),
                Flight(id = 1, iataCode = "MAD", name = "Madurai international Airport", passengers = 20),
                Flight(id = 1, iataCode = "LON", name = "London international Airport", passengers = 21)
            ))
        }
    }
}