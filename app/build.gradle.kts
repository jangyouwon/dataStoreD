plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-kapt")
}

android {
    namespace = "com.example.datastore"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.datastore"
        minSdk = 31
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
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

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")
    //Json converter
    implementation ("com.squareup.retrofit2:converter-scalars:2.9.0")
    //Gson
    implementation ("com.google.code.gson:gson:2.8.9")
    //Serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.2.0")
    // Preferences Data Store
    implementation ("androidx.datastore:datastore-preferences:1.0.0-alpha07")
    implementation ("androidx.datastore:datastore-core:1.0.0-alpha07")
    implementation("androidx.datastore:datastore:1.1.0")


    implementation("androidx.datastore:datastore:1.1.0")

    // optional - RxJava2 support
    implementation("androidx.datastore:datastore-rxjava2:1.1.0")

    // optional - RxJava3 support
    implementation("androidx.datastore:datastore-rxjava3:1.1.0")
    implementation ("androidx.datastore:datastore:1.0.0-alpha05")
    // LiveData
    implementation ("androidx.lifecycle:lifecycle-livedata-ktx:2.2.0")
}