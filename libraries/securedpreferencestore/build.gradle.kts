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
        getByName(BuildTypes.DEBUG){
            buildConfigField(BuildConstants.DATA_TYPE_INT, BuildConstants.VERSION_CODE, "13")
        }
        getByName(BuildTypes.RELEASE) {
            buildConfigField(BuildConstants.DATA_TYPE_INT, BuildConstants.VERSION_CODE, "13")
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
    implementation(Libraries.Androidx.APP_COMPAT)
}