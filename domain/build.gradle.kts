plugins {
    id("kotlin")
    alias(libs.plugins.seoj17.jvm.library)
    alias(libs.plugins.ksp)
}

dependencies{
    implementation(libs.coroutine.core)
    implementation(libs.hilt.core)
    ksp(libs.hilt.compiler)
}