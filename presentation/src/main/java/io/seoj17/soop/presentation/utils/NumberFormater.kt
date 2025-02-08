package io.seoj17.soop.presentation.utils

import java.util.Locale

object NumberFormater {
    fun format(num: Int): String {
        return when {
            num < 1_000 -> {
                num.toString()
            }

            num < 1_000_000 -> {
                val thousand = num / 1_000.0
                if (thousand % 1.0 == 0.0) {
                    "${thousand.toInt()}k"
                } else {
                    String.format(Locale.US, "%.1fk", thousand)
                }
            }

            else -> {
                val million = num / 1_000_000.0
                if (million % 1.0 == 0.0) {
                    "${million.toInt()}m"
                } else {
                    String.format(Locale.US, "%.1fm", million)
                }
            }
        }
    }
}
