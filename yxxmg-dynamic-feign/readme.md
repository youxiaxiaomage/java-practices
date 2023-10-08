#### 动态`feign` 基于serviceId（服务发现如nacos）

```java
public interface DynamicService {
    /**
     * POST请求
     *
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @PostMapping("{url}")
    String post(@PathVariable String url, @RequestBody Object params);

    /**
     * GET请求
     *
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @GetMapping("{url}")
    String get(@PathVariable String url, @SpringQueryMap Object params);

    /**
     * PUT请求
     *
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @PutMapping("{url}")
    String put(@PathVariable String url, @RequestBody Object params);

    /**
     * DELETE请求
     *
     * @param url 请求路径
     * @param params 请求参数
     * @return 响应结果
     */
    @DeleteMapping("{url}")
    String delete(@PathVariable String url, @RequestBody Object params);
}
```

```java
public class DynamicFeignClientFactory<T> {
    private final FeignClientBuilder feignClientBuilder;

    public DynamicFeignClientFactory(ApplicationContext applicationContext) {
        this.feignClientBuilder = new FeignClientBuilder(applicationContext);
    }

    public T getFeignClient(final Class<T> type, String serviceId) {
        return this.feignClientBuilder.forType(type, serviceId).build();
    }
}
```

```java

@RequiredArgsConstructor
public class DynamicClient {
    private final DynamicFeignClientFactory<DynamicService> dynamicFeignClientFactory;

    public String post(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).post(url, params);
    }

    public String get(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).get(url, params);
    }

    public String put(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).put(url, params);
    }

    public String delete(String serviceId, String url, Object params) {
        return dynamicFeignClientFactory.getFeignClient(DynamicService.class, serviceId).delete(url, params);
    }
}
```

#### 动态feign基于URL

例子展示的是按照`POST`请求方式

```java

@FeignClient(name = "dynamic-url-feign", url = "EMPTY")
public interface DynamicUrlClient {
    @RequestLine("POST /")
    @Headers({"Content-Type: application/json", "Accept: application/json"})
    String post(URI uri, @RequestBody String request);
}
```

```java

@Component
@Import(FeignClientsConfiguration.class)
public class DynamicUrlService {
    private final DynamicUrlClient dynamicUrlClient;

    @Autowired
    public DynamicUrlService(Encoder encoder, Decoder decoder) {
        this.dynamicUrlClient = Feign.builder()
                .encoder(encoder)
                .decoder(decoder)
                .target(Target.EmptyTarget.create(DynamicUrlClient.class));
    }

    public String post(String url, String request) {
        return this.dynamicUrlClient.post(URI.create(url), request);
    }
}
```

####  