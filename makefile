usage:
	@echo '==============================================================================================================='
	@echo 'usage                       :     显示本菜单'
	@echo 'clean                       :     清理本项目'
	@echo 'clean-build-source          :     清理构建逻辑'
	@echo 'compile                     :     编译项目'
	@echo 'build                       :     构建项目'
	@echo 'dist                        :     发布项目'
	@echo 'test                        :     执行单元测试'
	@echo 'dependencies                :     分析若干模块依赖关系'
	@echo 'add-java-license-header     :     为java文件加入许可证头信息'
	@echo 'setup-gradle-wrapper        :     设置gradle-wrapper'
	@echo 'github                      :     提交文件'
	@echo '==============================================================================================================='

clean:
	@$(CURDIR)/gradlew --quiet -p $(CURDIR) clean

clean-build-source:
	@$(CURDIR)/gradlew --quiet -p $(CURDIR)/build-src clean

compile:
	@$(CURDIR)/gradlew classes

build:
	@$(CURDIR)/gradlew -x test build

dist:
	@$(CURDIR)/gradlew -x test dist

test:
	@$(CURDIR)/gradlew test

setup-gradlew-wrapper:
	@$(CURDIR)/gradlew wrapper

add-java-license-header:
	@$(CURDIR)/gradlew addJavaLicenseHeader

dependencies:
	@$(CURDIR)/gradlew :projects-app:daemon:dependencies

github:
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"

.PHONY: usage \
	clean clean-build-source \
	compile build dist test \
	dependencies \
	setup-gradle-wrapper \
	add-java-license-header \
	github
