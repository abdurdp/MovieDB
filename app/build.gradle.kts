plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}
android {
    namespace = "task.abdur.moviedb"
    compileSdk = 33
    buildFeatures {
        buildConfig = true
    }
    defaultConfig {
        applicationId = "task.abdur.moviedb"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {

        val MOVIEDB_API_KEY = providers.gradleProperty("MOVIEDB_API_KEY")
        val MOVIEDB_BASE_URL = providers.gradleProperty("MOVIEDB_BASE_URL")
        val MOVIEDB_POSTER_URL = providers.gradleProperty("MOVIEDB_POSTER_URL")
        release {

            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField( "String", "API_KEY", "\"${MOVIEDB_API_KEY}\"")
            buildConfigField( "String", "MOVIEDB_BASE_URL", "\"${MOVIEDB_BASE_URL}\"")
            buildConfigField( "String", "MOVIEDB_POSTER_URL", "\"${MOVIEDB_POSTER_URL}\"")
        }
        debug {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
            buildConfigField( "String", "API_KEY", "\"${MOVIEDB_API_KEY}\"")
            buildConfigField( "String", "MOVIEDB_BASE_URL", "\"${MOVIEDB_BASE_URL}\"")
            buildConfigField( "String", "MOVIEDB_POSTER_URL", "\"${MOVIEDB_POSTER_URL}\"")
        }

    }
    buildFeatures {
        viewBinding = true
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

    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.6.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.6.0")
    implementation("androidx.navigation:navigation-fragment-ktx:2.6.0")
    implementation("androidx.navigation:navigation-ui-ktx:2.6.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    // Retrofit for making API calls
    implementation ("com.squareup.retrofit2:retrofit:2.9.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.9.0")

    // Coroutine support for Retrofit
    implementation ("com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:0.9.2")


    //coroutines
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation ("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.4")

    //lifecycle
    implementation ("androidx.lifecycle:lifecycle-viewmodel:2.6.2")
    implementation ("androidx.lifecycle:lifecycle-livedata:2.6.2")

    //Glide
    implementation ("com.github.bumptech.glide:glide:4.16.0")
    implementation ("com.opencsv:opencsv:5.5")
    implementation ("com.github.PhilJay:MPAndroidChart:v3.1.0")



}