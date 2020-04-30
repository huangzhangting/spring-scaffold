## zipkin 服务端
#### 启动方式
1、jar包：（https://repo1.maven.org/maven2/io/zipkin/java/zipkin-server/）
curl -sSL https://zipkin.io/quickstart.sh | bash -s
java -jar zipkin.jar

带存储方式，例如mysql：
java -jar zipkin-server-2.21.1-exec.jar 
--STORAGE_TYPE=mysql 
--MYSQL_HOST=127.0.0.1 
--MYSQL_TCP_PORT=3306 
--MYSQL_USER=root 
--MYSQL_PASS=root 
--MYSQL_DB=zipkin

2、docker：
docker run -d -p 9411:9411 openzipkin/zipkin

带存储方式，例如mysql：
docker run --name zipkin -d -p 9411:9411 
-e STORAGE_TYPE=mysql 
-e MYSQL_HOST=127.0.0.1 
-e MYSQL_TCP_PORT=3306 
-e MYSQL_USER=root 
-e MYSQL_PASS=root 
-e MYSQL_DB=zipkin 
openzipkin/zipkin

如果每次都要使用 docker 命令来分别启动 zipkin 容器还是略显繁琐，
可以通过 Docker Compose 进行启动，docker-compose.yml 文件内容如下：

version: '3'
services:
  zipkin:
    image: openzipkin/zipkin
    container_name: zipkin
    environment:
      - STORAGE_TYPE=mysql
      - MYSQL_HOST=127.0.0.1
      - MYSQL_TCP_PORT=3306
      - MYSQL_USER=root
      - MYSQL_PASS=root
      - MYSQL_DB=zipkin
      #- RABBIT_ADDRESSES=127.0.0.1:5672
      #- RABBIT_USER=admin
      #- RABBIT_PASSWORD=123
    ports:
      - 9411:9411


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


### 数据存储方式
1、默认方式，内存中

2、mysql：
相关表：https://github.com/openzipkin/zipkin/blob/master/zipkin-storage/mysql-v1/src/main/resources/mysql.sql

3、Elasticsearch

启动方式：https://github.com/openzipkin/zipkin-dependencies
