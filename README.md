## 基于SpringBoot的技术demo，欢迎新想法。

### 一、springboot-api版本控制
[raven-version-control](https://github.com/RavenHuo/SpringAttempt/tree/master/raven-version-control/)
- (1)接口url前缀版本匹配。
- (2)接口版本降级匹配。

### 二、基于SpringCloud fegin及hystrix 的数据加密校验插件
[raven-api-encryption](https://github.com/RavenHuo/SpringAttempt/tree/master/api-encryption-feign/)
- （1）微服务间通过feign（RestTemplate）相互调用的数据加密
- （2）接口api对称加密与对称解密
- （3）接口api数据解密校验
feign接口：对称加密


### 三、 springcloud-动态路由配置
[zuul-dynamic-routing](https://github.com/RavenHuo/SpringAttempt/tree/master/zuul-dynamic-routing/)
 动态zuul路由配置，将路由配置写入数据库，30秒刷新一次路由配置 
 避免每次新增服务都需要重启zuul