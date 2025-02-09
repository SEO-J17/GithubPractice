plugins {
    alias(libs.plugins.seoj17.android.library)
    alias(libs.plugins.seoj17.hilt)
}

android {
    namespace = "io.seoj17.soop.domain"
}

dependencies {
    implementation(project(":data"))
    implementation(libs.bundles.coroutine)
    implementation(libs.androidx.paging.common)
}
