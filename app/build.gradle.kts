plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.edwindiaz.agroclima"
    compileSdk = 35  // ACTUALIZA A 35

    defaultConfig {
        applicationId = "com.edwindiaz.agroclima"
        minSdk = 26
        targetSdk = 35  // ACTUALIZA A 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
}

kotlin {
    jvmToolchain(11)
}

dependencies {
    // Core Android - USA VERSIONES COMPATIBLES CON API 34
    implementation("androidx.core:core-ktx:1.12.0")  // VERSIÓN MÁS ANTIGUA
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.6.2")  // VERSIÓN MÁS ANTIGUA
    implementation("androidx.activity:activity-compose:1.8.2")  // VERSIÓN MÁS ANTIGUA

    // Compose BOM - USA UNA VERSIÓN MÁS ANTIGUA
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))  // VERSIÓN ANTERIOR
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.material:material-icons-extended")

    // ViewModel - VERSIÓN COMPATIBLE
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2")

    // Navigation
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Retrofit
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coil para imágenes
    implementation("io.coil-kt:coil-compose:2.6.0")

    // Location
    implementation("com.google.android.gms:play-services-location:21.2.0")
    implementation(libs.androidx.lifecycle.runtime.compose)

    // Testing
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.08.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}