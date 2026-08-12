package br.com.mymovieslist.core.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.mymovielist.theme.MyMoviesTheme
import mymovieslist.app.generated.resources.Res
import mymovieslist.app.generated.resources.error_drawable
import mymovieslist.app.generated.resources.ops_something_went_wrong
import mymovieslist.app.generated.resources.retry
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

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
        ErrorMessage()
        Spacer(modifier = Modifier.weight(1f))
        Button(modifier = Modifier.fillMaxWidth(), onClick = tryAgain) {
            Text(
                stringResource(Res.string.retry),
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun ErrorMessage() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painterResource(Res.drawable.error_drawable),
            contentDescription = "",
            contentScale = ContentScale.Crop
        )
        Text(
            modifier = Modifier.padding(8.dp),
            text = stringResource(Res.string.ops_something_went_wrong)
        )
    }
}

@Preview
@Composable
fun RetryScreenPreview() {
    MyMoviesTheme {
        RetryScreen {}
    }
}
