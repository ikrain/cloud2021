## 消费模块（端口80）

- 包含controller、config、entities
- 由于entities包中的内容与其他模块的相同，因此重构到03-cloud-api-commons模块中，将03-cloud-api-commons模块install到本地库，在不同模块的pom文件中加入依赖即可使用

