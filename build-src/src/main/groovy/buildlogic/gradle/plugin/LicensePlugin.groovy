package buildlogic.gradle.plugin

import org.gradle.api.DefaultTask
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.tasks.StopExecutionException
import org.gradle.api.tasks.TaskAction

import javax.inject.Inject

class LicensePlugin implements Plugin<Project> {

	@Override
	void apply(Project project) {
		project.extensions.add(AddLicenseHeaderTask.TASK_NAME, new Config())
		project.tasks.register(AddLicenseHeaderTask.TASK_NAME, AddLicenseHeaderTask, project)
	}

	static class AddLicenseHeaderTask extends DefaultTask {

		public static final String TASK_NAME = 'addLicenseHeader'

		private final Project project

		@Inject
		AddLicenseHeaderTask(Project project) {
			this.project = project
			this.group = 'license'
			this.description = 'Adds a license header for source codes'
		}

		@TaskAction
		void execute() {
			var config = project.extensions.getByName(TASK_NAME) as Config
			var javaHeader = config.javaHeader
			if (!javaHeader.endsWith('\n')) {
				javaHeader = javaHeader + '\n'
			}

			if (javaHeader == null || javaHeader.isBlank()) {
				throw new StopExecutionException('配置错误 javaHeader缺失')
			}

			project.fileTree(project.rootDir) {
				include('**/*.java')
			}.each { file ->
				var content = file.text
				if (!content.startsWith(javaHeader)) {
					content = javaHeader + content
					file.setText(content)
				}
			}
		}
	}

	static class Config {
		public String javaHeader = ''
	}

}
