#### Jackson序列化与反序列化

1. 字段脱敏

   ```java
   @Retention(RetentionPolicy.RUNTIME)
   @Target(ElementType.FIELD)
   @JacksonAnnotationsInside
   @JsonSerialize(using = SensitiveJsonSerializer.class)
   public @interface Sensitive {
       SensitiveStrategy strategy();
   }
   ```

   ```java
   public class SensitiveJsonSerializer extends JsonSerializer<String> implements ContextualSerializer {
       private SensitiveStrategy strategy;
   
       @Override
       public void serialize(String s, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
           throws IOException {
           jsonGenerator.writeString(strategy.desensitizer().apply(s));
       }
   
       @Override
       public JsonSerializer<?> createContextual(SerializerProvider serializerProvider, BeanProperty beanProperty)
           throws JsonMappingException {
           Sensitive annotation = beanProperty.getAnnotation(Sensitive.class);
           if (Objects.nonNull(annotation) && Objects.equals(String.class, beanProperty.getType().getRawClass())) {
               this.strategy = annotation.strategy();
               return this;
           }
           return serializerProvider.findValueSerializer(beanProperty.getType(), beanProperty);
       }
   }
   ```

   ```java
   public enum SensitiveStrategy {
       /**
        * 手机
        */
       PHONE(DesensitizedUtil::mobilePhone), ID(id -> DesensitizedUtil.idCardNum(id, 3, 4)),
       EMAIL(DesensitizedUtil::email);
   
       private final Function<String, String> desensitizer;
   
       SensitiveStrategy(Function<String, String> desensitizer) {
           this.desensitizer = desensitizer;
       }
   
       public Function<String, String> desensitizer() {
           return desensitizer;
       }
   }
   ```

   ```java
   @Data
   @Accessors(chain = true)
   public class Student implements Serializable {
       private static final long serialVersionUID = 1270380814231996333L;
       private String userId;
       @Sensitive(strategy = SensitiveStrategy.PHONE)
       private String phoneNumber;
       @Sensitive(strategy = SensitiveStrategy.EMAIL)
       private String email;
   }
   ```


2. 请求去首尾空格

   ```java
   public class TrimStringSerializer extends JsonSerializer<String> {
       @Override
       public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
           gen.writeString(StringUtils.trim(value));
       }
   }
   ```

   ```java
   @Configuration
   public class JacksonConfig {
       @Bean
       public ObjectMapper objectMapper() {
           // 创建一个 ObjectMapper 对象
           ObjectMapper objectMapper = new ObjectMapper();
           // 创建一个 SimpleModule 对象，用于注册序列化器
           SimpleModule module = new SimpleModule();
           // 将自定义的 TrimStringSerializer 序列化器注册到 SimpleModule 对象中
           module.addSerializer(String.class, new TrimStringSerializer());
           // 将 SimpleModule 对象注册到 ObjectMapper 中
           objectMapper.registerModule(module);
           // 返回 ObjectMapper 对象
           return objectMapper;
       }
   }
   ```

####  