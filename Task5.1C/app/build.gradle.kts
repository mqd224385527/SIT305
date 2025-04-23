plugins {
    id("com.android.application")
}

android {
    namespace = "com.example.task51c"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.task51c"
        minSdk = 15
        targetSdk = 22
        versionCode = 1
        versionName = "1.0"
    }

    buildTypes {
        getByName("debug") {
            isMinifyEnabled = false
        }
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
}

dependencies {
    implementation("com.android.support:appcompat-v7:22.2.1")
    implementation("com.android.support:support-v4:22.2.1")
}
