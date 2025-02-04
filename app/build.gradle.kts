plugins {
    alias(libs.plugins.seoj17.android.application)
    alias(libs.plugins.seoj17.android.application.compose)
    alias(libs.plugins.seoj17.hilt)
}

android {
    namespace = "io.seoj17.soop.app"

    defaultConfig {
        applicationId = "io.seoj17.soop"
        versionCode = 1
        // X.Y.Z; X = Major, Y = minor, Z = Patch level
        versionName = "0.0.1"
    }
}

dependencies {
    implementation(project(":data"))
    implementation(project(":domain"))
    implementation(project(":presentation"))
}