package io.seoj17.soop.presentation.ui.search.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.input.TextFieldLineLimits
import androidx.compose.foundation.text.input.TextFieldState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun SearchTextField(
    modifier: Modifier,
    textFieldState: TextFieldState,
    textHint: String = "",
    keyboardType: KeyboardType = KeyboardType.Text,
) {
    BasicTextField(
        modifier = modifier,
        state = textFieldState,
        lineLimits = TextFieldLineLimits.SingleLine,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        onKeyboardAction = { /*완료버튼을 눌렀을때 실행할 함수*/ },
        cursorBrush = Brush.linearGradient(
            colors = listOf(Color.Black, Color.Transparent),
        ),
        decorator = { innerTextField ->
            Box(
                modifier = modifier
                    .drawBehind {
                        drawLine(
                            color = Color.Black,
                            start = Offset(0f, size.height),
                            end = Offset(size.width, size.height),
                            strokeWidth = 1.dp.toPx()
                        )
                    }
                    .padding(vertical = 15.dp, horizontal = 20.dp),
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