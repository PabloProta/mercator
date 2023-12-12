package com.tomartin.mercator.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Menu
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.tomartin.mercator.ui.viewmodels.GlanceCountryViewmodel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GlanceCountryScreen(glanceCountryViewmodel: GlanceCountryViewmodel = viewModel()) {
    var text by rememberSaveable {
        mutableStateOf("")
    }
    var active by rememberSaveable {
        mutableStateOf(false)
    }
    val uiState by glanceCountryViewmodel.uiState.collectAsState()
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SearchBar(
                query = text,
                onQueryChange = { text = it },
                onSearch = { active = true },
                active = active,
                onActiveChange = { active = it },
                placeholder = { Text(text = "Search countries") },
                leadingIcon = {
                    if (!active)
                        IconButton(onClick = { /*TODO*/ }) {
                            Icon(imageVector = Icons.Rounded.Menu, contentDescription = null)
                        }
                    else
                        IconButton(onClick = { active = false }) {
                            Icon(imageVector = Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                        }
                },
                trailingIcon = { Icon(imageVector = Icons.Rounded.Search, contentDescription = null) }
            ) {
                
            }
            LazyColumn(
                modifier = Modifier.padding(top = 10.dp, start = 8.dp, end = 8.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                uiState.atlasData?.let { glanceCountryModels ->
                    items(glanceCountryModels.size) {
                        Column {
                            Row(modifier = Modifier.clickable {  }) {
                                AsyncImage(
                                    model = glanceCountryModels[it].flags.png,
                                    contentDescription = null,
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .size(75.dp),
                                    contentScale = ContentScale.Crop,
                                )
                                Spacer(modifier = Modifier.width(8.dp))
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .height(75.dp),
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = glanceCountryModels[it].name.common)
                                    Text(text = "%,d".format(glanceCountryModels[it].population))
                                }
                            }
                            Spacer(modifier = Modifier.height(5.dp))
                            HorizontalDivider(modifier = Modifier.fillMaxWidth())
                            Spacer(modifier = Modifier.height(5.dp))
                        }
                    }
                }
            }
        }
    }
}