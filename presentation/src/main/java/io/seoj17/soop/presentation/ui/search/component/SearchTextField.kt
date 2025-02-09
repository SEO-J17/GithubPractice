package io.seoj17.soop.presentation.ui.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    modifier: Modifier,
    textFieldState: TextFieldState,
    onClickConfirm: () -> Unit = {},
    textHint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    val focusManager = LocalFocusManager.current

    BasicTextField(
        modifier = modifier
            .height(50.dp)
            .drawBehind {
                drawLine(
                    color = Color.Black,
                    start = Offset(0f, size.height),
                    end = Offset(size.width, size.height),
                    strokeWidth = 1.dp.toPx(),
                )
            },
        state = textFieldState,
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = ImeAction.Done),
        onKeyboardAction = {
            focusManager.clearFocus()
            onClickConfirm()
        },
        cursorBrush = Brush.linearGradient(
            colors = listOf(Color.Black, Color.Transparent),
        ),
        decorator = { innerTextField ->
            Box(
                modifier = Modifier.padding(vertical = 10.dp, horizontal = 20.dp),
                contentAlignment = Alignment.CenterStart,
            ) {
                if (textFieldState.text.isEmpty()) {
                    Text(
                        text = textHint,
                        textAlign = TextAlign.Center,
                        color = Color.LightGray,
                    )
                }
                innerTextField()
            }
        },
    )
}
