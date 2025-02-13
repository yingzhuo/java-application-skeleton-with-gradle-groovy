usage:
	@echo '==============================================================================================================='
	@echo 'usage:                         显示本菜单'
	@echo 'information:                   显示项目信息'
	@echo 'clean:                         清理本项目'
	@echo 'compile:                       编译项目'
	@echo 'build:                         构建项目'
	@echo 'gradle-wrapper:                初始化 gradle-wrapper'
	@echo 'github:                        提交文件'
	@echo '==============================================================================================================='

information:
	@$(CURDIR)/gradlew -q :information

clean:
	@$(CURDIR)/gradlew clean

compile:
	@$(CURDIR)/gradlew classes

build:
	@$(CURDIR)/gradlew -x test build

gradle-wrapper:
	@gradle wrapper

github: clean
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"

.PHONY: usage information clean compile build gradle-wrapper github
