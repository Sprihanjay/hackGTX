plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {
    namespace = "com.example.thementai"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.thementai"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures{
        viewBinding=true
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
}

dependencies {

    implementation ("androidx.compose.ui:ui:1.0.3")
    implementation ("androidx.compose.material:material:1.0.3")
    implementation ("androidx.activity:activity-compose:1.3.1")
    implementation ("androidx.cardview:cardview:1.0.0")

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.10.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.navigation:navigation-fragment:2.5.3")
    implementation("androidx.navigation:navigation-ui:2.5.3")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    dependencies {
        // CameraX core library using the camera2 implementation
        val cameraxVersion = "1.4.0-alpha01"
        implementation("androidx.camera:camera-core:${cameraxVersion}")
        implementation("androidx.camera:camera-camera2:${cameraxVersion}")
        implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
        implementation("androidx.camera:camera-video:${cameraxVersion}")
        implementation("androidx.camera:camera-view:${cameraxVersion}")
        implementation("androidx.camera:camera-mlkit-vision:${cameraxVersion}")
        implementation("androidx.camera:camera-extensions:${cameraxVersion}")
        implementation("androidx.core:core-ktx:1.6.0")


    }

}