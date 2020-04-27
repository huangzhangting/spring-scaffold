## zipkin 服务端
#### 启动方式
1、jar包：
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar

2、docker：
docker run -d -p 9411:9411 openzipkin/zipkin

#### 上报方式
1、http方式： yml配置如下：

spring:  
  zipkin:
    base-url: http://localhost:9411/ # 指定了 Zipkin 服务器的地址

2、消息总线：例如 RabbitMQ


### zipkin客户端 spring cloud sleuth
在 Spring Cloud Sleuth 中对 Zipkin 的整合进行了自动化配置的封装

yml配置如下：

spring:  
  sleuth:
    web:
      client:
        enabled: true
    sampler:
      probability: 1.0 # 将采样比例设置为 1.0，也就是全部都需要。默认是 0.1
  zipkin:
    base-url: http://localhost:9411/ # 指定了 Zipkin 服务器的地址

