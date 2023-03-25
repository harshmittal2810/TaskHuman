/**
 * Created by Harsh Mittal on 01/07/22.
 */
object Libraries {
    object Kotlin {
        const val LIBRARY = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.KOTLIN}"
        const val CORE = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.COROUTINES}"
        const val COROUTINES = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.COROUTINES}"
    }

    object Androidx { // All the AndroidX libraries
        const val APP_COMPAT = "androidx.appcompat:appcompat:${Versions.APP_COMPAT}"
        const val ANNOTATIONS = "androidx.annotation:annotation:${Versions.SUPPORT}"
        const val CONSTRAINT_LAYOUT = "androidx.constraintlayout:constraintlayout:2.1.4"
        const val KOTLIN_EXTENSIONS = "androidx.core:core:1.8.0"
        const val CARD_VIEW = "androidx.cardview:cardview:1.0.0"
        const val RECYCLER_VIEW = "androidx.recyclerview:recyclerview:1.2.1"


        // SQLite library
        const val SQL_LITE = "androidx.sqlite:sqlite:2.2.0"

        // Swipe to refresh layout library
        const val SWIPE_REFRESH_LAYOUT = "androidx.swiperefreshlayout:swiperefreshlayout:1.1.0"

        // Android Crypto security library
        const val SECURITY = "androidx.security:security-crypto:1.1.0-alpha03"
        //For Biometric authentication
        const val BIOMETRIC = "androidx.biometric:biometric:1.1.0"
    }

    object Google {
        const val MATERIAL_DESIGN = "com.google.android.material:material:1.6.1"

        const val ANALYTICS = "com.google.android.gms:play-services-analytics:18.0.1"

        // Google play update library
        const val PLAY_CORE = "com.google.android.play:core:1.10.3"

        // GSON library
        const val GSON = "com.google.code.gson:gson:2.9.0"
        const val STARTUP_RUNTIME = "androidx.startup:startup-runtime:1.1.1"
    }

    object Firebase {
        const val FBOM = "com.google.firebase:firebase-bom:30.1.0"
        const val CORE = "com.google.firebase:firebase-core"
        const val CONFIG = "com.google.firebase:firebase-config"
        const val FCM = "com.google.firebase:firebase-messaging"
        const val FPM = "com.google.firebase:firebase-perf-ktx"
        const val ANALYTICS = "com.google.firebase:firebase-analytics-ktx"
        const val CRASHLYTICS = "com.google.firebase:firebase-crashlytics-ktx"
        const val DYNAMIC_LINKS = "com.google.firebase:firebase-dynamic-links-ktx"
    }

    object JetPack {
        // Lifecycle library dependencies
        const val VIEW_MODEL = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.0"
        const val LIVE_DATA = "androidx.lifecycle:lifecycle-livedata-ktx:2.5.0"
        const val ANNOTATIONS = "androidx.lifecycle:lifecycle-compiler:2.5.0"
        const val LIFECYCLE_EXTENSION = "androidx.lifecycle:lifecycle-extensions:2.2.0"
        const val ACTIVITY_EXTENSION = "androidx.activity:activity-ktx:1.6.1"
        const val LIFECYCLE_SAVED_STATE = "androidx.lifecycle:lifecycle-viewmodel-savedstate:2.5.0"
        const val LIFECYCLE_PROCESS = "androidx.lifecycle:lifecycle-process:2.5.0"
        const val LIFECYCLE_SERVICE = "androidx.lifecycle:lifecycle-service:2.5.0"
        const val FRAGMENT_VIEW_MODEL = "androidx.fragment:fragment-ktx:1.5.1"
        const val NAVIGATION_FRAGMENT =
            "androidx.navigation:navigation-fragment-ktx:${Versions.NAVIGATION}"
        const val NAVIGATION_UI = "androidx.navigation:navigation-ui-ktx:${Versions.NAVIGATION}"

        // Room ORM library
        object Room {
            const val CORE = "androidx.room:room-runtime:${Versions.ROOM}"
            const val ANNOTATIONS = "androidx.room:room-compiler:${Versions.ROOM}"
            const val KOTLIN_EXTENSIONS = "androidx.room:room-ktx:${Versions.ROOM}"
            const val TESTING = "androidx.room:room-testing:${Versions.ROOM}"
        }

        // Work-manager library
        const val WORK_MANAGER = "androidx.work:work-runtime-ktx:2.7.1"

        // Paging library
        const val PAGING = "androidx.paging:paging-runtime:${Versions.PAGING}"
        const val PAGING_KOTLIN = "androidx.paging:paging-runtime-ktx:${Versions.PAGING}"
        const val PAGING_RXJAVA = "androidx.paging:paging-rxjava2:${Versions.PAGING}"

    }

    object Hilt {
        // Version
        private const val LIFE_CYCLE_VERSION = "1.0.0"

        // Core dependencies
        const val CORE = "com.google.dagger:hilt-android:${Versions.HILT}"
        const val ANNOTATIONS = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

        // When using Kotlin with lifecycle.
        const val VIEW_MODEL_ANNOTATIONS = "androidx.hilt:hilt-compiler:$LIFE_CYCLE_VERSION"

        // Work Manager
        const val WORK_MANAGER = "androidx.hilt:hilt-work:$LIFE_CYCLE_VERSION"
    }

    object Retrofit {
        private const val RETROFIT_VERSION = "2.9.0"
        private const val OK_HTTP_VERSION = "4.10.0"

        // Retrofit library dependencies
        const val CORE = "com.squareup.retrofit2:retrofit:$RETROFIT_VERSION"
        const val JSON_CONVERTER = "com.squareup.retrofit2:converter-gson:$RETROFIT_VERSION"
        const val OK_HTTP = "com.squareup.okhttp3:okhttp:$OK_HTTP_VERSION"
        const val LOGGING = "com.squareup.okhttp3:logging-interceptor:$OK_HTTP_VERSION"
        const val MOCK_SERVER = "com.squareup.okhttp3:mockwebserver:$OK_HTTP_VERSION"
        const val SCALARS = "com.squareup.retrofit2:converter-scalars:$RETROFIT_VERSION"
        const val RX_JAVA = "com.squareup.retrofit2:adapter-rxjava2:$RETROFIT_VERSION"
    }

    object LeakCanary {
        const val LEAKCANARY = "com.squareup.leakcanary:leakcanary-android:2.9.1"
    }

    object RxJava {
        private const val RX_JAVA = "3.0.0"

        const val CORE = "io.reactivex.rxjava3:rxjava:3.0.4"
        const val ANDROID = "io.reactivex.rxjava3:rxandroid:$RX_JAVA"
        const val KOTLIN = "io.reactivex.rxjava3:rxkotlin:$RX_JAVA"
    }

    object Testing {
        const val JUNIT5 = "org.junit.jupiter:junit-jupiter-api:5.7.0"
        const val JUNIT5JupiterEngine = "org.junit.jupiter:junit-jupiter-engine:5.7.0"
        const val JUNIT5JupiterParams = "org.junit.jupiter:junit-jupiter-params:5.7.0"
        const val JUNIT5VintageEngine = "org.junit.vintage:junit-vintage-engine:5.7.0"
        const val junitPlatformConsole = "org.junit.platform:junit-platform-console:1.7.0"
    }

    object UnitTest {
        // Core library
        const val androidxCore = "androidx.arch.core:core-testing:2.1.0"

        // Kotlin Coroutines
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"

        // JUnit 4 framework
        const val junit = "junit:junit:4.13.2"
        const val truth = "com.google.truth:truth:1.1.2"

        // Hamcrest library
        const val hamcrest = "org.hamcrest:hamcrest-library:1.3"

        // Robolectric environment
        const val robolectric = "org.robolectric:robolectric:4.4"
        const val androidx = "androidx.test:core-ktx:1.4.0"

        // Mockito framework
        const val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"

        // Mock server
        const val mockServer = "com.squareup.okhttp3:mockwebserver:4.9.1"

        // Room Database
        const val room = "androidx.room:room-testing:${Versions.ROOM}"

        // Hilt testing dependency
        const val hilt = "com.google.dagger:hilt-android-testing:${Versions.HILT}"

        // Make Hilt generate code in the androidTest folder
        const val hiltAnnotations = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

        // org.json library
        const val JSON = "org.json:json:20210307"
    }

    object InstrumentationTest {

        // Core library
        const val androidxArch = "androidx.arch.core:core-testing:2.1.0"
        const val androidxCore = "androidx.test:core-ktx:1.3.0"

        // AndroidJUnitRunner and JUnit Rules
        const val androidxTestRunner = "androidx.test:runner:1.3.0"
        const val androidxTestRules = "androidx.test:rules:1.3.0"

        // Fragment test
        const val fragments = "androidx.fragment:fragment-testing:1.5.0"

        // Junit
        const val junit = "junit:junit:4.13.2"

        // Assertions
        const val junitAndroid = "androidx.test.ext:junit:1.1.2"
        const val androidxTruth = "androidx.test.ext:truth:1.3.0"
        const val truth = "com.google.truth:truth:1.1.2"

        private const val espresso_version = "3.3.0"

        // Espresso dependencies
        const val espresso = "androidx.test.espresso:espresso-core:$espresso_version"
        const val espressoContrib = "androidx.test.espresso:espresso-contrib:$espresso_version"
        const val espressoAccessibility = "androidx.test.espresso:espresso-accessibility:$espresso_version"
        // Espresso Web allows you to test WebView components contained within an
        // activity. It uses the WebDriver API to inspect and control the behavior
        // of a WebView.
        const val espressoWeb = "androidx.test.espresso:espresso-web:$espresso_version"
        const val espressoIdlingConcurrent = "androidx.test.espresso.idling:idling-concurrent:$espresso_version"

        // Espresso Intents enables validation and stubbing of intents sent out by an app.
        // With Espresso Intents, you can test an app, activity, or service in isolation by
        // intercepting outgoing intents, stubbing the result, and sending it back to the
        // component under test.
        const val espressoIntents = "androidx.test.espresso:espresso-intents:$espresso_version"

        // The following Espresso dependency can be either "implementation"
        // or "androidTestImplementation", depending on whether you want the
        // dependency to appear on your APK's compile classpath or the test APK
        // classpath.
        const val espressoIdlingResource = "androidx.test.espresso:espresso-idling-resource:$espresso_version"

        // Hamcrest library
        const val hamcrest = "org.hamcrest:hamcrest-library:1.3"

        // UI testing with UI Automator
        const val uiautomator = "androidx.test.uiautomator:uiautomator:2.2.0"

        // Hilt testing dependency
        const val hilt = "com.google.dagger:hilt-android-testing:${Versions.HILT}"

        // Make Hilt generate code in the androidTest folder
        const val hiltAnnotations = "com.google.dagger:hilt-android-compiler:${Versions.HILT}"

        // Mock server
        const val mockServer = "com.squareup.okhttp3:mockwebserver:4.9.1"

        // Kotlin Coroutines
        const val coroutines = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.COROUTINES}"

        // Mockito framework
        const val mockito = "org.mockito:mockito-core:${Versions.MOCKITO}"
        const val mockitoAndroid = "org.mockito:mockito-android:${Versions.MOCKITO}"
    }

    object Glide {
        private const val VERSION = "4.14.2"
        const val GLIDE = "com.github.bumptech.glide:glide:$VERSION"
        const val ANNOTATIONS = "com.github.bumptech.glide:compiler:$VERSION"
    }

    /** All other miscellaneous library dependencies will come here */
    object Other {
        const val TIMBER = "com.jakewharton.timber:timber:5.0.1"

        const val EXPANDABLE_TEXTVIEW = "com.ms-square:expandableTextView:0.1.4"
        const val LEGACY_SUPPORT = "androidx.legacy:legacy-support-v4:1.0.0"
        const val RFAB = "com.github.wangjiegulu:rfab:2.0.0"
        const val REACTIVE_NETWORK = "com.github.pwittchen:reactivenetwork-rx2:3.0.0"
        const val RX_PERMISSION = "com.github.tbruyelle:rxpermissions:0.12"
        const val EASY_PERMISSION = "pub.devrel:easypermissions:0.3.0"
        const val BROWSER = "androidx.browser:browser:1.3.0"
        const val CIRCLE_IMAGEVIEW = "de.hdodenhof:circleimageview:3.0.1"
        const val ZOOM_LAYOUT = "com.otaliastudios:zoomlayout:1.7.0"
        const val REACTIONS = "io.zla:android-reactions:1.0"
        const val DRAWER_BEHAVIOR = "com.infideap.drawerbehavior:drawer-behavior:1.0.4"
        const val PALETTE = "androidx.palette:palette-ktx:1.0.0"
        const val PHOTO_VIEW = "com.bm.photoview:library:1.4.1"
        const val MULTI_DEX = "androidx.multidex:multidex:2.0.1"
        const val THREE_TEN = "com.jakewharton.threetenabp:threetenabp:1.1.1"
        const val LOTTIE = "com.airbnb.android:lottie:5.2.0"
        const val UCROP = "com.github.yalantis:ucrop:2.2.6"
    }

    object Shimmer{
        const val SHIMMER_LAYOUT = "com.facebook.shimmer:shimmer:0.5.0"
        const val SHIMMER_RECYCLERVIEW = "com.todkars:shimmer-recyclerview:0.4.1"
    }

    object Security {
        const val SQL_CIPHER = "net.zetetic:android-database-sqlcipher:4.4.0"
//        const val SAFETY_NET = "com.google.android.gms:play-services-safetynet:17.0.0"
        const val SAFE_CONTENT_RESOLVER = "de.cketti.safecontentresolver:safe-content-resolver-v21:0.9.0"
    }

    object Modules {
        const val LOGGER = ":libraries:logger"
        const val PREFERENCES = ":libraries:preferences"
        const val SECURED_PREFERENCES = ":libraries:securedpreferencestore"
    }
}