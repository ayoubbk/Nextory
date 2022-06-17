package com.nextory.testapp.ui.bookdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage


@Composable
fun BookDetailsScreen(
    viewModel: BookDetailsViewModel = hiltViewModel(),
    onPopBackStack: () -> Unit
) {

    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = {
            BookDetailsTopBar(
                topAppBarText = viewModel.book?.title!!,
                onBackPressed = onPopBackStack
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                AsyncImage(
                    model = viewModel.book?.imageUrl,
                    contentDescription = viewModel.book?.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                        .clip(MaterialTheme.shapes.medium),
                )
                
                Text(text = viewModel.book?.title!!)
                Text(text = viewModel.book?.author!!)
                Text(text = viewModel.book?.description!!)
            }
        }

    )

}




@Composable
fun BookDetailsTopBar(topAppBarText: String, onBackPressed: () -> Unit) {
    TopAppBar(
        modifier = Modifier.windowInsetsPadding(
            WindowInsets.safeDrawing.only(
                WindowInsetsSides.Horizontal + WindowInsetsSides.Top
            )
        ),
        title = {
            Text(
                text = topAppBarText,
                textAlign = TextAlign.Center,
            )
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(
                    Icons.Default.ArrowBack,
                    contentDescription = "back"
                )
            }
        },
        actions = {
            IconButton(onClick = {  }) {
                Icon(
                    Icons.Default.Favorite,
                    contentDescription = "bookmark"
                )
            }
        }
    )
}

