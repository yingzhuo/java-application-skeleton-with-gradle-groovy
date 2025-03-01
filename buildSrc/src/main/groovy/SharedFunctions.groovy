import org.gradle.api.GradleException
import org.gradle.api.Project

import java.time.LocalDateTime

/**
 * 构建逻辑的共享函数
 */
class SharedFunctions {

	private SharedFunctions() {
		super();
	}

	static String getTimestamp(String formatPattern = 'yyyyMMddHHmmss') {
		return LocalDateTime.now().format(formatPattern)
	}

	static List<String> getLeafProjectNames(Project rootProject) {
		return rootProject.allprojects
			.findAll {
				it.subprojects.isEmpty()
			}
			.collect {
				it.displayName
					.replace("project '", "")
					.replace("'", "")
			}
	}

	static String getGradleProperty(Project project, String propertyName, String defaultPropertyValue = null) {
		var value = project.providers.gradleProperty(propertyName).getOrElse(defaultPropertyValue)
		if (value == null) {
			throw new GradleException("Cannot get value of name: ${propertyName}")
		}
		return value
	}

	static boolean getGradlePropertyAsBoolean(Project project, String propertyName, boolean defaultValue = false) {
		var booleanString = getGradleProperty(project, propertyName, "${defaultValue}")
		return Boolean.valueOf(booleanString)
	}

	static String getEnv(String name, String defaultValueIfMissing = '<no value>') {
		return Optional.of(System.getenv(name)).orElse(defaultValueIfMissing)
	}

	static boolean getEnvAsBoolean(String name, boolean defaultValue = false) {
		return getEnv(name, "${defaultValue}").toBoolean()
	}

}
