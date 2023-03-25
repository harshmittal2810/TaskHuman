import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Harsh Mittal on 01/07/22.
 */
object AppConfig {

    const val COMPILE_SDK_VERSION = 33
    const val MIN_SDK_VERSION = 24
    const val TARGET_SDK_VERSION = 33
    const val APPLICATION_ID = "com.harsh.taskhuman"
    const val APPLICATION_ID_TESTING = "com.harsh.taskhumantesting"
    const val USE_VECTOR_DRAWABLE_SUPPORT = true
    const val TEST_INSTRUMENTATION_RUNNER = "androidx.test.runner.AndroidJUnitRunner"

    // All the versions
    private const val VERSION_MAJOR = 1
    private const val VERSION_MINOR = 0
    private const val VERSION_PATCH = 0
    private const val VERSION_REVISION = 0

    const val VERSION_CODE = 1
    const val VERSION_NAME = "$VERSION_MAJOR.$VERSION_MINOR.$VERSION_PATCH.$VERSION_REVISION"

    fun date(): String = SimpleDateFormat("ddMMyyyy", Locale.ENGLISH).format(Date())
}
