package buildlogic.gradle

import org.gradle.api.Project

class InformationPlugin extends AbstractPlugin {

	private static final String TASK_NAME_INFORMATION = 'information'

	@Override
	void apply(Project project) {
		registerTaskInfo(project)
	}

	private void registerTaskInfo(Project project) {
		setExtensionsBean(project, TASK_NAME_INFORMATION, new ConfigData())
		project.tasks.register(TASK_NAME_INFORMATION) { task ->
			task.group = 'help'
			task.description = 'Show project information'
			task.doLast {
				ConfigData config = getExtensionsBean(project, TASK_NAME_INFORMATION)
				println(config.text)
			}
		}
	}

	// -----------------------------------------------------------------------------------------------------------------

	/**
	 * extensions of this plugin
	 */
	static class ConfigData implements Serializable {
		String text = '<no value>'
	}

}
