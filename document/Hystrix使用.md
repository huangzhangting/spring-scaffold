### Hystrix
相关配置：https://github.com/Netflix/Hystrix/wiki/Configuration

##### 1、直接使用
1）在工程的启动类中加入 @EnableCircuitBreaker 开启熔断器功能；
2）@HystrixCommand 注解来指定回调方法，
  例如：@HystrixCommand(fallbackMethod = "addServiceFallback")

##### 2、通过Feign使用Hystrix

