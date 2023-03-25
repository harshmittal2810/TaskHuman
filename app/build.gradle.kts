import com.google.firebase.crashlytics.buildtools.gradle.CrashlyticsExtension
import com.google.firebase.perf.plugin.FirebasePerfExtension

plugins {
    id(Plugins.APPLICATION)
    kotlin(Plugins.ANDROID)
    id(Plugins.KOTLIN_PARCELIZE)
    kotlin(Plugins.KAPT)
    id(Plugins.GOOGLE_SERVICES)
    id(Plugins.FIREBASE_PREF)
    id(Plugins.FIREBASE_CRASH)
    id(Plugins.NAVIGATION_SAFE)
    id(Plugins.HILT)
    id(Plugins.JUNIT5)
    id("kotlin-android")

}

android {
    namespace = AppConfig.APPLICATION_ID
    compileSdk = AppConfig.COMPILE_SDK_VERSION

    buildFeatures {
        dataBinding = true
        viewBinding = true
    }
    bundle {
        language {
            enableSplit = false
        }
    }

    defaultConfig {
        applicationId = AppConfig.APPLICATION_ID
        minSdk = AppConfig.MIN_SDK_VERSION
        targetSdk = AppConfig.TARGET_SDK_VERSION
        versionCode = AppConfig.VERSION_CODE
        versionName = AppConfig.VERSION_NAME
        multiDexEnabled = true
        vectorDrawables.useSupportLibrary = AppConfig.USE_VECTOR_DRAWABLE_SUPPORT
        javaCompileOptions {
            annotationProcessorOptions {
                arguments["room.incremental"] = "true"
            }
        }

        signingConfigs {
            named("debug").configure {
                storeFile = file("${System.getProperty("user.home")}/.android/debug.keystore")
            }
            register("release") {
                storeFile = file("${System.getProperty("user.home")}/.android/debug.keystore")
            }
        }
    }

    useLibrary("android.test.runner")
    useLibrary("android.test.base")
    useLibrary("android.test.mock")

    aaptOptions.cruncherEnabled = false

    sourceSets.getByName("main") {
        SourceSets.resourceDirectories.forEach { directory ->
            res.srcDir("src/main/java/${directory}/res")
        }
        java.srcDir("src/main/java")
        java.srcDir("src/main/kotlin")
    }

    lint {
        isAbortOnError = false
        isCheckAllWarnings = false
        isCheckReleaseBuilds = false
        isIgnoreWarnings = true
        isQuiet = true
    }

    buildTypes {

        getByName(BuildTypes.DEBUG) {
            isDebuggable = true
            isMinifyEnabled = false
            isShrinkResources = false
            signingConfig = signingConfigs.getByName("debug")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.LOGGING_ENABLED, "true")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.DISABLE_LOG_WRITING, "false")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.ENABLE_DEBUGGING_LOGS, "true")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.NETWORK_LOGGING_ENABLED, "true")

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = false          // to disable mapping file uploads (default=true if minifying)
            }

            configure<FirebasePerfExtension> { setInstrumentationEnabled(false) }
        }


        getByName(BuildTypes.RELEASE) {
            isDebuggable = false
            isMinifyEnabled = false
            isShrinkResources = false
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.LOGGING_ENABLED, "true")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.DISABLE_LOG_WRITING, "false")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.ENABLE_DEBUGGING_LOGS, "true")
            buildConfigField(BuildConstants.DATA_TYPE_BOOLEAN, BuildConstants.NETWORK_LOGGING_ENABLED, "true")

            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            signingConfig = signingConfigs.getByName("debug")

            configure<CrashlyticsExtension> {
                mappingFileUploadEnabled = true          // to disable mapping file uploads (default=true if minifying)
                nativeSymbolUploadEnabled = true         // to enable NDK symbol file uploading (default=false)
                unstrippedNativeLibsDir = "path/to/libs" // optional override to change the default unstripped native library path, only used in NDK builds
            }
        }

        create(BuildTypes.QA) {
            initWith(getByName(BuildTypes.DEBUG))
            // Specifies a sorted list of fallback build types that the
            // plugin should try to use when a dependency does not include a
            // "staging" build type. You may specify as many fallbacks as you
            // like, and the plugin selects the first build type that's
            // available in the dependency.
            setMatchingFallbacks("debug", "release")
        }

        create(BuildTypes.STAGING) {
            initWith(getByName(BuildTypes.DEBUG))
            setMatchingFallbacks("debug", "release")
        }

        create(BuildTypes.LOCALHOST) {
            initWith(getByName(BuildTypes.DEBUG))
            setMatchingFallbacks("debug", "release")
        }

        create(BuildTypes.OTHER_SERVER) {
            initWith(getByName(BuildTypes.DEBUG))
            setMatchingFallbacks("debug", "release")
        }

        create(BuildTypes.PROD) {
            initWith(getByName(BuildTypes.RELEASE))
            setMatchingFallbacks("release")
        }
    }

    flavorDimensions.add("client")
    productFlavors {
        create(ProductFlavors.DEFAULT)
//        create(ProductFlavors.DEV) {
//            applicationId = AppConfig.APPLICATION_ID_TESTING
//        }
    }

    splits.abi {
        isEnable = true
        reset()
        isUniversalApk = false
    }

    dexOptions.preDexLibraries = false
    dexOptions.javaMaxHeapSize = "4g"

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    ndkVersion = "21.3.6528147"

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_11.toString()
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
            isReturnDefaultValues = true
        }
        animationsDisabled = true
    }

    packagingOptions {
        resources.excludes.apply {
            add("META-INF/DEPENDENCIES.txt")
            add("META-INF/NOTICE")
            add("META-INF/NOTICE.txt")
            add("META-INF/NOTICE.md")
            add("META-INF/LICENSE")
            add("META-INF/LICENSE.md")
            add("META-INF/LICENSE.txt")
            add("META-INF/androidx.")
            add("META-INF/com.google.android.material_material.version")
            add("META-INF/.kotlin_module")
            add("META-INF/services/com.fasterxml.jackson.core.JsonFactory")
            add("META-INF/services/org.apache.commons.logging.LogFactory")
            add("META-INF/rxjava.properties")
        }
    }

    applicationVariants.all {
        val variant = this
        val buildFields = buildconfig.BuildConfigFieldFacade.getBuildFields(
            variant.flavorName,
            variant.buildType.name
        )
        buildFields.forEach { buildField ->
            buildConfigField(buildField.dataType, buildField.key, buildField.value)
        }

        outputs.map {
            val output = it as com.android.build.gradle.internal.api.BaseVariantOutputImpl
            output.outputFileName = output.outputFileName
                .replace("app-${variant.flavorName}-${variant.buildType.name}", "${variant.flavorName.toUpperCase()
                }-${variant.buildType.name.toUpperCase()}-")
                .replace(".apk", "v${variant.versionName}-${AppConfig.date()}.apk")
        }
    }
}

/**
 * Some annotation processors (such as AutoFactory) rely on precise types
 * in declaration signatures. By default, KAPT replaces every unknown type
 * (including types for the generated classes) to NonExistentClass, but
 * you can change this behavior. So, the following additional flag
 * will enable error type inferring in stubs.
 */
kapt {
    correctErrorTypes = true
}

dependencies {
    // Android + Kotlin dependencies
    implementation(Libraries.Kotlin.LIBRARY)
    implementation(Libraries.Kotlin.CORE)
    implementation(Libraries.Kotlin.COROUTINES)

    // Androidx dependencies
    implementation(Libraries.Androidx.APP_COMPAT)
    implementation(Libraries.Androidx.CONSTRAINT_LAYOUT)
    implementation(Libraries.Androidx.KOTLIN_EXTENSIONS)
    implementation(Libraries.Androidx.CARD_VIEW)
    implementation(Libraries.Androidx.RECYCLER_VIEW)
    implementation(Libraries.Androidx.ANNOTATIONS)
    implementation(Libraries.Androidx.SWIPE_REFRESH_LAYOUT)
    implementation(Libraries.Androidx.BIOMETRIC)

    // Others
    implementation(Libraries.Other.EXPANDABLE_TEXTVIEW)
    implementation(Libraries.Other.LEGACY_SUPPORT)
    implementation(Libraries.Other.RFAB)
    implementation(Libraries.Other.REACTIVE_NETWORK)
    implementation(Libraries.Other.RX_PERMISSION)
    implementation(Libraries.Other.BROWSER)
    implementation(Libraries.Other.CIRCLE_IMAGEVIEW)
    implementation(Libraries.Other.ZOOM_LAYOUT)
    implementation(Libraries.Other.REACTIONS)
    implementation(Libraries.Other.DRAWER_BEHAVIOR)
    implementation(Libraries.Other.PALETTE)
    implementation(Libraries.Other.MULTI_DEX)
    implementation(Libraries.Other.THREE_TEN)
    implementation(Libraries.Other.LOTTIE)

    // Security
//    implementation(Libraries.Security.SAFETY_NET)
    implementation(Libraries.Security.SAFE_CONTENT_RESOLVER)

    // Database file encryption library
    implementation(Libraries.Security.SQL_CIPHER)

    // SQLite support library
    implementation(Libraries.Androidx.SQL_LITE)

    // EncryptedPreferences library
    implementation(Libraries.Androidx.SECURITY)

    // Google dependencies
    implementation(Libraries.Google.MATERIAL_DESIGN)
    implementation(Libraries.Google.ANALYTICS)
    implementation(Libraries.Google.PLAY_CORE)
    implementation(Libraries.Google.GSON)
    implementation(Libraries.Google.STARTUP_RUNTIME)

    //shimmer
    implementation(Libraries.Shimmer.SHIMMER_LAYOUT)
    implementation(Libraries.Shimmer.SHIMMER_RECYCLERVIEW)

    // Firebase dependencies
    implementation(platform(Libraries.Firebase.FBOM))
    implementation(Libraries.Firebase.CORE)
    implementation(Libraries.Firebase.CONFIG)
    implementation(Libraries.Firebase.FCM)
    implementation(Libraries.Firebase.FPM)
    implementation(Libraries.Firebase.ANALYTICS)
    implementation(Libraries.Firebase.CRASHLYTICS)
    implementation(Libraries.Firebase.DYNAMIC_LINKS)

    // Room database library
    implementation(Libraries.JetPack.Room.CORE)
    implementation(Libraries.JetPack.Room.KOTLIN_EXTENSIONS)
    implementation("androidx.appcompat:appcompat:1.5.0")
    implementation("com.google.android.material:material:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    kapt(Libraries.JetPack.Room.ANNOTATIONS)

    // Work manager library
    implementation(Libraries.JetPack.WORK_MANAGER)

    // JetPack library dependencies
    implementation(Libraries.JetPack.NAVIGATION_FRAGMENT)
    implementation(Libraries.JetPack.NAVIGATION_UI)

    // Lifecycle library dependencies
    implementation(Libraries.JetPack.VIEW_MODEL)
    implementation(Libraries.JetPack.LIVE_DATA)
    kapt(Libraries.JetPack.ANNOTATIONS)
    implementation(Libraries.JetPack.LIFECYCLE_EXTENSION)
    implementation(Libraries.JetPack.ACTIVITY_EXTENSION)
    implementation(Libraries.JetPack.LIFECYCLE_SAVED_STATE)
    implementation(Libraries.JetPack.LIFECYCLE_PROCESS)
    implementation(Libraries.JetPack.LIFECYCLE_SERVICE)
    implementation(Libraries.JetPack.FRAGMENT_VIEW_MODEL)

    // Paging library
    implementation(Libraries.JetPack.PAGING)
    implementation(Libraries.JetPack.PAGING_KOTLIN)
    implementation(Libraries.JetPack.PAGING_RXJAVA)

    // Retrofit network library dependencies
    implementation(Libraries.Retrofit.CORE)
    implementation(Libraries.Retrofit.OK_HTTP)
    implementation(Libraries.Retrofit.JSON_CONVERTER)
    implementation(Libraries.Retrofit.LOGGING)
    implementation(Libraries.Retrofit.SCALARS)
    implementation(Libraries.Retrofit.RX_JAVA)

    // Rx-Java2 library
    implementation(Libraries.RxJava.CORE)
    implementation(Libraries.RxJava.ANDROID)
    implementation(Libraries.RxJava.KOTLIN)

    // Hilt library dependencies
    implementation(Libraries.Hilt.CORE)
    implementation(Libraries.Hilt.WORK_MANAGER)
    kapt(Libraries.Hilt.ANNOTATIONS)
    kapt(Libraries.Hilt.VIEW_MODEL_ANNOTATIONS)

    // Glide library dependencies
    implementation(Libraries.Glide.GLIDE)
    kapt(Libraries.Glide.ANNOTATIONS)

    // Logging
    implementation(Libraries.Other.TIMBER)
    implementation(Libraries.Other.UCROP)

    // Leak canary library
//    library(BuildTypes.DEBUG, Libraries.LeakCanary.LEAKCANARY)

    //Logger library
    implementation(project(Libraries.Modules.LOGGER))

    // Preferences
    implementation(project(Libraries.Modules.PREFERENCES))
    implementation(project(Libraries.Modules.SECURED_PREFERENCES))


    /**********************************************************************/
    // Test dependencies
    testImplementation(Libraries.Testing.JUNIT5)
    testImplementation(Libraries.Testing.JUNIT5JupiterEngine)
    testImplementation(Libraries.Testing.JUNIT5JupiterParams)
    testImplementation(Libraries.Testing.JUNIT5VintageEngine)
    testImplementation(Libraries.Testing.junitPlatformConsole)

    // Test dependencies
    testImplementation(Libraries.UnitTest.androidxCore)
    testImplementation(Libraries.UnitTest.coroutines)
    testImplementation(Libraries.UnitTest.junit)
    testImplementation(Libraries.UnitTest.truth)
    testImplementation(Libraries.UnitTest.robolectric)
    testImplementation(Libraries.UnitTest.androidx)
    testImplementation(Libraries.UnitTest.mockito)
    testImplementation(Libraries.UnitTest.mockServer)
    testImplementation(Libraries.UnitTest.room)
    testImplementation(Libraries.UnitTest.hilt)
    kaptTest(Libraries.UnitTest.hiltAnnotations)
    testImplementation(Libraries.UnitTest.JSON)

    // Instrumentation test dependencies
    androidTestImplementation(Libraries.InstrumentationTest.androidxArch)
    androidTestImplementation(Libraries.InstrumentationTest.androidxCore)
    androidTestImplementation(Libraries.InstrumentationTest.androidxTestRunner)
    androidTestImplementation(Libraries.InstrumentationTest.androidxTestRules)
    library(BuildTypes.DEBUG, Libraries.InstrumentationTest.fragments)
    androidTestImplementation(Libraries.InstrumentationTest.junit)
    androidTestImplementation(Libraries.InstrumentationTest.junitAndroid)
    androidTestImplementation(Libraries.InstrumentationTest.androidxTruth)
    androidTestImplementation(Libraries.InstrumentationTest.truth)
    androidTestImplementation(Libraries.InstrumentationTest.espresso)
    androidTestImplementation(Libraries.InstrumentationTest.espressoContrib)
    androidTestImplementation(Libraries.InstrumentationTest.espressoAccessibility)
    androidTestImplementation(Libraries.InstrumentationTest.espressoWeb)
    androidTestImplementation(Libraries.InstrumentationTest.espressoIdlingConcurrent)
    androidTestImplementation(Libraries.InstrumentationTest.espressoIntents)
    androidTestImplementation(Libraries.InstrumentationTest.espressoIdlingResource)
    androidTestImplementation(Libraries.InstrumentationTest.hamcrest)
    androidTestImplementation(Libraries.InstrumentationTest.uiautomator)
    androidTestImplementation(Libraries.InstrumentationTest.hilt)
    androidTestImplementation(Libraries.InstrumentationTest.mockServer)
    androidTestImplementation(Libraries.InstrumentationTest.coroutines)
    kaptAndroidTest(Libraries.InstrumentationTest.hiltAnnotations)

}

/**
 * Adds a dependency to the custom build type 'implementation' configuration.
 *
 * @param buildType build variant to load the dependency to be added.
 * @param dependencyNotation notation for the dependency to be added.
 * @return The dependency.
 *
 * @see [DependencyHandler.add]
 */
fun DependencyHandler.library(buildType: String, dependencyNotation: Any): Dependency? =
    add("${buildType}Implementation", dependencyNotation)
