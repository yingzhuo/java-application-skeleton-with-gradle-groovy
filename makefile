usage:
	@echo '==============================================================================================================='
	@echo 'usage                       :     显示本菜单'
	@echo 'clean                       :     清理本项目'
	@echo 'compile                     :     编译项目'
	@echo 'build                       :     构建项目'
	@echo 'setup-gradle-wrapper      :     初始化 gradle-wrapper'
	@echo 'github                      :     提交文件'
	@echo '==============================================================================================================='

clean:
	@gradle clean

compile:
	@gradle classes

build:
	@gradle -x test build

setup-gradle-wrapper:
	@gradle wrapper

github: clean
	@git status
	@git add .
	@git commit -m "$(shell /bin/date "+%F %T")"
	@git push

.PHONY: usage clean compile build \
	setup-gradle-wrapper \
	github
