### feign
目前，在Spring cloud 中服务之间通过restful方式调用有两种方式： 
- restTemplate + Ribbon 
- feign

采用feign的方式更优雅（feign内部也使用了ribbon做负载均衡）。

Feign是对Ribbon和Hystrix的整合。

#### 常用配置
feign:
  hystrix:
    enabled: true

ribbon:
  OkToRetryOnAllOperations: false #对所有操作请求都进行重试,默认false
  ReadTimeout: 5000    # ribbon read超时时间
  ConnectTimeout: 5000 #ribbon请求连接的超时时间，默认值2000
  MaxAutoRetries: 0     #对当前实例的重试次数，默认0
  MaxAutoRetriesNextServer: 0 #对切换实例的重试次数，默认1
 
hystrix:
  threadpool:
    default:
      # 核心线程池大小  默认10
      coreSize: 20
      # 最大最大线程池大小
      maximumSize: 30
      # 此属性允许maximumSize的配置生效。 那么该值可以等于或高于coreSize。 设置coreSize <maximumSize会创建一个线程池，该线程池可以支持maximumSize并发，但在相对不活动期间将向系统返回线程。 （以keepAliveTimeInMinutes为准）
      allowMaximumSizeToDivergeFromCoreSize: true
      # 请求等待队列
      maxQueueSize: 10
      # 队列大小拒绝阀值 在还未超过请求等待队列时也会拒绝的大小
      queueSizeRejectionThreshold: 10
  command:
    LimitCheckApi#rcsLimitCheck(RpcRequest):  #default全局有效 默认值为 commonKey commonKey生成方法在 Feign.configKey(target.type(), method) 中
      fallback:
        enabled: true
      execution:
        timeout:
          #如果enabled设置为false，则请求超时交给ribbon超时时间为准,为true,则超时以熔断时间为准
          enabled: true
        isolation:
          #隔离策略，有THREAD和SEMAPHORE
          #THREAD - 它在单独的线程上执行，并发请求受线程池中的线程数量的限制
          #SEMAPHORE - 它在调用线程上执行，并发请求受到信号量计数的限制
          #对比：https://www.cnblogs.com/java-synchronized/p/7927726.html
          thread:
            timeoutInMilliseconds: 800 #断路器超时时间，默认1000ms
    LimitCheckApi#testTimeOutFallBack(long):
      fallback:
        enabled: true
      execution:
        timeout:
            #如果enabled设置为false，则请求超时交给ribbon超时时间为准,为true,则超时以熔断时间为准
            enabled: true
        isolation:
          #隔离策略，有THREAD和SEMAPHORE
          #THREAD - 它在单独的线程上执行，并发请求受线程池中的线程数量的限制
          #SEMAPHORE - 它在调用线程上执行，并发请求受到信号量计数的限制
          #对比：https://www.cnblogs.com/java-synchronized/p/7927726.html
          thread:
            timeoutInMilliseconds: 800 #断路器超时时间，默认1000ms
            

