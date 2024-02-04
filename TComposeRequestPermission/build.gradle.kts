plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("maven-publish")
    id("signing")
}

apply(plugin = "maven-publish")
apply(plugin = "signing")

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}


afterEvaluate {
    tasks.withType<PublishToMavenLocal> {
        // Make 'publishReleasePublicationToMavenLocal' depend on 'assembleRelease'
        dependsOn("assembleRelease")
    }
    publishing {

        publications.create<MavenPublication>("release") {
            groupId = "io.github.the-best-is-best"
            artifactId = "compose-request-permission"
            version = "1.0.0"
            from(components["release"])



            //  artifact("$buildDir/outputs/aar/ComposeQuill-release.aar")
            //artifact("$buildDir/libs/ComposeQuill-release.jar")
            // Provide artifacts information required by Maven Central
            pom {
                name.set("Compose Request Permission")
                description.set("A Compose library for android , that provides a permission request easily.")
                url.set("https://github.com/the-best-is-best/compose-request-permission")
                licenses {
                    license {
                        name.set("Apache-2.0")
                        url.set("https://opensource.org/licenses/Apache-2.0")
                    }
                }
                issueManagement {
                    system.set("Github")
                    url.set("https://github.com/the-best-is-best/compose-request-permission/issues")
                }
                scm {
                    connection.set("https://github.com/the-best-is-best/compose-request-permission.git")
                    url.set("https://github.com/the-best-is-best/compose-request-permission")
                }
                developers {
                    developer {
                        id.set("MichelleRaouf")
                        name.set("Michelle Raouf")
                        email.set("eng.michelle.raouf@gmail.com")
                    }
                }
            }
        }
        repositories {

            maven {
                name = "OSSRH-snapshots"
                url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
                credentials {
                    username = System.getenv("MAVEN_NAME")
                    password = System.getenv("MAVEN_TOKEN")
                }
            }
//            maven {
//                name = "LocalMaven"
//                url = uri("$buildDir/maven")
            //   }
//                maven {
//                    name = "GitHubPackages"
//                    url = uri("https://maven.pkg.github.com/the-best-is-best/ComposeQuill")
//                    credentials {
//                        username = "the-best-is-best"
//                        password =
//                            System.getenv("BUILD_MAVEN")
//                    }
//                }


        }

    }

}


signing {
    useGpgCmd()
    sign(publishing.publications)
}

android {
    namespace = "io.tbib.composerequestpermission"
    compileSdk = 34

    defaultConfig {
        minSdk = 21

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
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1"
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
    implementation("androidx.activity:activity-compose:1.8.2")
    implementation(platform("androidx.compose:compose-bom:2023.08.00"))

    implementation("com.google.accompanist:accompanist-permissions:0.34.0")
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.11.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}