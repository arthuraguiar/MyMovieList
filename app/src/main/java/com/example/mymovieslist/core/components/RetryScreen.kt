package com.example.mymovieslist.core.components

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mymovieslist.R.drawable
import com.example.mymovieslist.R.string
import com.example.theme.MyMoviesTheme

@Composable
fun RetryScreen(tryAgain: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(modifier = Modifier.weight(1f))
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Image(
                painterResource(drawable.error_drawable),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(8.dp),
                text = stringResource(id = string.ops_something_went_wrong)
            )
        }
        Spacer(modifier = Modifier.weight(1f))
        Button(modifier = Modifier.fillMaxWidth(), onClick = tryAgain) {
            Text(
                stringResource(id = string.retry),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Preview("Retry screen", uiMode = Configuration.UI_MODE_NIGHT_NO)
@Preview("Retry screen (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun RetryScreenPreview() {
    MyMoviesTheme {
        RetryScreen {}
    }
}