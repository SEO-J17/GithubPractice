plugins {
    alias(libs.plugins.seoj17.android.library)
    alias(libs.plugins.seoj17.hilt)
}

android {
    namespace = "io.seoj17.soop.data"
}

dependencies {
    implementation(libs.bundles.coroutine)
    implementation(libs.bundles.network)
}