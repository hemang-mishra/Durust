plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.google.android.libraries.mapsplatform.secrets.gradle.plugin)
    alias(libs.plugins.google.gms.google.services)
    alias(libs.plugins.kotlin.serialization)
}


android {
    namespace = "com.tripod.durust"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.tripod.durust"
        minSdk = 26
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    buildFeatures {
        compose = true
        buildConfig = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}


dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.compose)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    implementation(libs.generativeai)
    implementation(libs.firebase.auth)
    implementation(libs.androidx.ui.text.google.fonts)
    implementation(libs.googleid)
    implementation(libs.androidx.viewpager2)
    implementation(libs.firebase.firestore.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
    implementation(libs.androidx.ui)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.androidx.constraintlayout.compose)
    implementation(libs.kotlinx.serialization.json)
    implementation(libs.androidx.connect.client)
    implementation(libs.androidx.navigation.compose)
    implementation(libs.androidx.core.splashscreen)
    implementation(libs.vico.compose)
    implementation(libs.vico.compose.m2)
    implementation(libs.vico.compose.m3)
    implementation(libs.vico.core)
    implementation("com.cuberto:liquid-swipe:1.0.0")
    implementation("androidx.viewpager2:viewpager2:1.1.0")
    implementation("io.coil-kt:coil-compose:2.6.0")
    implementation("com.airbnb.android:lottie-compose:6.4.1")
}