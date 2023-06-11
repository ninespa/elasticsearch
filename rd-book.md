## 笔记

## 本地打包
- 去除settings.gradle中docker相关配置：见git log

## 本地运行
- 从打包后的文件提取相关配置
> cd Documents/elasticsearch-7.16.4-SNAPSHOT
>
> cp -r config modules plugins /Users/your_name/Documents/eshome
- 配置启动jvm参数
```
-Des.path.home=/Users/your_name/Documents/eshome -Des.path.conf=/Users/your_name/Documents/eshome/config -Xms1g -Xmx1g -Dlog4j2.disable.jmx=true -Djava.security.policy=/Users/your_name/Documents/eshome/config/elasticsearch.policy
```
- 新增配置文件elasticsearch.policy在jvm参数指定
```
grant {
	permission javax.management.MBeanTruxtPermission "register";
	permission javax.management.MBeanServerPermission "createMBeanServer";
	permission java.lang.RuntimePermission "createClassLoader";
	permission java.lang.RuntimePermission "getClassLoader";
	permission java.lang.RuntimePermission "setContextClassLoader";
	permission org.elasticsearch.secure_sm.ThreadPermission "modifyArbitraryThreadGroup";
	permission org.elasticsearch.secure_sm.ThreadPermission "modifyArbitraryThread";
};

```
- 注销security.policy中codeBase相关配置，见git log
