package io.github.yuu0922.lrinsight.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.yuu0922.lrinsight.R

@Composable
fun RetryableErrorDialog(
    error: String,
    retry: () -> Unit
) {
    AlertDialog(
        onDismissRequest = {},
        title = {
            Text(
                text = stringResource(R.string.error),
                color = MaterialTheme.colorScheme.error
            )
        },
        text = {
            SelectionContainer {
                Text(text = error)
            }
        },
        confirmButton = {
            Text(
                text = stringResource(R.string.retry),
                modifier = Modifier.clickable { retry }
            )
        }
    )
}