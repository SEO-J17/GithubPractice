plugins {
    alias(libs.plugins.seoj17.hilt)
    alias(libs.plugins.seoj17.android.library)
    alias(libs.plugins.seoj17.android.library.compose)
}

android {
    namespace = "io.seoj17.soop.presentation"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(libs.androidx.compose.ui)
    implementation(libs.androidx.compose.ui.graphics)
    implementation(libs.androidx.compose.material3)
    implementation(libs.androidx.appcompat)
    // coroutine
    implementation(libs.bundles.coroutine)
    // hilt navigation compose
    implementation(libs.hilt.navigation.compose)
    // navigation compose
    implementation(libs.navigation.compose)
    // compose coil
    implementation(libs.coil.compose)
    // compose paging
    implementation(libs.androidx.paging.compose)
}
