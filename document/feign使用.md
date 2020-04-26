### feign
目前，在Spring cloud 中服务之间通过restful方式调用有两种方式： 
- restTemplate + Ribbon 
- feign

采用feign的方式更优雅（feign内部也使用了ribbon做负载均衡）。

