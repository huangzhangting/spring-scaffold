### gateway

与注册中心配合使用，简单配置如下：

spring:
  cloud:
    gateway:
      discovery:
        locator:
          enabled: true # 是否与服务注册于发现组件进行结合，通过 serviceId 转发到具体的服务实例
          lowerCaseServiceId: true # 将请求路径上的服务名配置为小写

##### url访问例子：
http://localhost:8888/consul-service-user/hello

访问的是gateway的端口8888，会自动转发给接口提供者 consul-service-user/hello

