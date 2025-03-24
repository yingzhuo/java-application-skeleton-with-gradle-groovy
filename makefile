usage:
	@echo '==============================================================================================================='
	@echo 'usage                       :     显示本菜单'
	@echo 'clean                       :     清理本项目'
	@echo 'clean-buildsrc              :     清理构建逻辑'
	@echo 'compile                     :     编译项目'
	@echo 'build                       :     构建项目'
	@echo 'test                        :     执行单元测试'
	@echo 'add-license-header          :     为java文件加入许可证头信息'
	@echo 'setup-gradle-wrapper        :     设置gradle-wrapper'
	@echo 'github                      :     提交文件'
	@echo '==============================================================================================================='

clean:
	@$(CURDIR)/gradlew -q -p $(CURDIR) 'clean'

clean-buildsrc:
	@$(CURDIR)/gradlew -q -p $(CURDIR)/buildSrc 'clean'

compile:
	@$(CURDIR)/gradlew 'classes'

build:
	@$(CURDIR)/gradlew 'build' -x 'test' -x 'check'

test:
	@$(CURDIR)/gradlew 'test'

setup-gradlew-wrapper:
	@$(CURDIR)/gradlew 'wrapper'

add-license-header:
	@$(CURDIR)/gradlew 'addLicenseHeader'

github:
	@$(CURDIR)/gradlew 'github'

.PHONY: usage \
	clean clean-buildsrc \
	compile build test \
	setup-gradle-wrapper \
	add-license-header \
	github
