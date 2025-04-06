import org.gradle.api.Project

import java.nio.file.Files
import java.nio.file.StandardCopyOption
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

	static void mkdir(File file) {
		file.mkdir()
	}

	static void copyFile(File src, File dest) {
		Files.copy(src.toPath(), dest.toPath(), StandardCopyOption.REPLACE_EXISTING)
	}

}
