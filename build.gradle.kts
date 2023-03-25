buildscript {
    repositories {
        google()
        jcenter()
        maven("https://www.jitpack.io")
        mavenLocal()
    }

    dependencies {
        classpath(Classpath.GRADLE)
        classpath(Classpath.KOTLIN)
        classpath(Classpath.MAVEN_GRADLE)
        classpath(Classpath.JFROG)
        classpath(Classpath.GOOGLE_SERVICES)
        classpath(Classpath.FIREBASE_PREF)
        classpath(Classpath.NAVIGATION)
        classpath(Classpath.FIREBASE_CRASHLYTICS)
        classpath(Classpath.HILT)
        classpath(Classpath.JUNIT5)

    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven("https://www.jitpack.io")
        mavenLocal()
    }
}

task("clean") {
    delete(rootProject.buildDir)
}