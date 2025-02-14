import org.gradle.api.Project

/**
 * 构建逻辑的共享函数
 */
abstract class SharedFunctions {

	static List<String> getLeafProjectNames(Project rootProject) {
		if (rootProject == null) {
			return []
		}

		final var list = []
		rootProject.subprojects.forEach { level1 ->
			level1.subprojects.forEach { level2 ->
				list.add(":${level1.name}:${level2.name}")
			}
		}
		return list
	}

	static String getGradleProperty(Project project, String propertyName, String defaultPropertyValue = null) {
		var value = project.providers.gradleProperty(propertyName).getOrElse(defaultPropertyValue)
		if (value == null) {
			throw new IllegalArgumentException("Cannot get value of name: ${propertyName}")
		}
		return value
	}

}
