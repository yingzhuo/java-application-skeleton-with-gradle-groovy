usage:
	@echo '==============================================================================================================='
	@echo 'usage                       :     显示本菜单'
	@echo 'clean                       :     清理本项目'
	@echo 'clean-buildsrc              :     清理构建逻辑'
	@echo 'compile                     :     编译项目'
	@echo 'build                       :     构建项目'
	@echo 'docker                      :     构建docker镜像'
	@echo 'test                        :     执行单元测试'
	@echo 'setup-gradle-wrapper        :     设置gradle-wrapper'
	@echo 'remove-gradlew-wrapper      :     删除gradle-wrapper'
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

docker:
	@$(CURDIR)/gradlew 'buildDockerImage' -x 'test' -x 'check'

test:
	@$(CURDIR)/gradlew 'test'

setup-gradlew-wrapper:
	@gradle 'wrapper'

remove-gradlew-wrapper:
	@$(CURDIR)/gradlew 'removeWrapper'

github:
	@$(CURDIR)/gradlew 'github'

.PHONY: usage \
	clean clean-buildsrc \
	compile build docker test \
	setup-gradle-wrapper remove-gradlew-wrapper \
	github
