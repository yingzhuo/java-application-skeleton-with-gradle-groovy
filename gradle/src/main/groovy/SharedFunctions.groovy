import org.gradle.api.Project

/**
 * 构建逻辑的共享函数
 */
abstract class SharedFunctions {

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
			throw new IllegalArgumentException("Cannot get value of name: ${propertyName}")
		}
		return value
	}

	static boolean getGradlePropertyAsBoolean(Project project, String propertyName, boolean defaultValue = false) {
		var booleanString = getGradleProperty(project, propertyName, "${defaultValue}")
		return Boolean.valueOf(booleanString)
	}

}
