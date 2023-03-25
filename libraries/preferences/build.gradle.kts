plugins {
    id(Plugins.LIBRARY)
    kotlin(Plugins.ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
}

android {
    compileSdk = AppConfig.COMPILE_SDK_VERSION

    defaultConfig {
        minSdk = AppConfig.MIN_SDK_VERSION
        targetSdk = AppConfig.TARGET_SDK_VERSION
        testInstrumentationRunner = AppConfig.TEST_INSTRUMENTATION_RUNNER
    }

    buildTypes {

        getByName(BuildTypes.DEBUG)

        getByName(BuildTypes.RELEASE) {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    testOptions {
        unitTests.isReturnDefaultValues = true
    }
}

dependencies {
    // Android + Kotlin dependencies
    implementation(Libraries.Kotlin.LIBRARY)
    implementation(Libraries.Kotlin.CORE)

    // EncryptedPreferences library
    implementation(Libraries.Androidx.SECURITY)

    // Unit test dependencies
    testImplementation(Libraries.UnitTest.junit)

    // Instrumentation test dependencies
    testImplementation(Libraries.InstrumentationTest.junit)
    androidTestImplementation(Libraries.InstrumentationTest.androidxArch)
    androidTestImplementation(Libraries.InstrumentationTest.androidxCore)
    androidTestImplementation(Libraries.InstrumentationTest.androidxTestRunner)
    androidTestImplementation(Libraries.InstrumentationTest.androidxTestRules)
    androidTestImplementation(Libraries.InstrumentationTest.junit)
    androidTestImplementation(Libraries.InstrumentationTest.junitAndroid)
}