plugins {
    id(Plugins.JAVA_LIBRARY)
    id(Plugins.KOTLIN_LIBRARY)
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

dependencies {
    implementation(Libraries.Kotlin.LIBRARY)
}