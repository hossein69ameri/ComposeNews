plugins {
    libs.plugins.apply {
        alias(android.library)
        alias(kotlin.android)
        alias(kotlin.parcelize)
    }
}

android {
    namespace = "ir.kaaveh.domain"
    compileSdk = 33

    defaultConfig {
        minSdk = 23

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}

dependencies {
    api(project(":library:core-test"))
    libs.apply {
        implementation(javax.inject)
        implementation(coroutines)
        implementation(lifecycle.viewmodel.ktx)
        testImplementation(junit)
        testImplementation(coroutines.test)
        testImplementation(mokito.kotlin)
    }
}