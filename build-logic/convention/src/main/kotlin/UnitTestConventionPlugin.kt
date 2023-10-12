import com.pokemon.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.dependencies

class UnitTestConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {

            dependencies {
                "api"(libs.findLibrary("testing.junit").get())
                "api"(libs.findLibrary("testing.instantiator").get())
                "api"(libs.findLibrary("kotlin.testing.coroutines").get())
                "api"(libs.findLibrary("testing.mockk").get())
                "api"(libs.findLibrary("testing.mock.webserver").get())
                "api"(libs.findLibrary("testing.turbine").get())
                "api"(libs.findLibrary("androidx.test.runner").get())
                "api"(libs.findLibrary("androidx.junit.ktx").get())
            }
        }
    }
}