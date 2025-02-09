package io.seoj17.soop.presentation.utils

import android.content.Context
import android.widget.Toast

fun Context.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun Context.showToast(messageResId: Int) {
    Toast.makeText(this, messageResId, Toast.LENGTH_SHORT).show()
}
