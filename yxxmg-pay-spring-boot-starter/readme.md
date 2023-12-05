#### 手写支付`SPI` `Starter`

![image-20221202110333902](../images/image-20221202110333902.png)

1. `spi`定义的接口要在增加`META-INF/services`下`com.yxxmg.pay.spi.PayService`

   ```properties
   com.yxxmg.pay.spi.AlipayServiceImpl=
   com.yxxmg.pay.spi.UnionPayServiceImpl=
   com.yxxmg.pay.spi.WechatPayServiceImpl=
   ```

2. 自定义配置类

   ```java
   @ConfigurationProperties(prefix = "yxxmg.pay")
   @Data
   public class YxxmgPayProperties {
       PayMethod payMethod;
   }
   ```

3. 自动装配类

   ```java
   @Configuration
   @ConditionalOnMissingBean(PayService.class)
   @EnableConfigurationProperties(YxxmgPayProperties.class)
   public class YxxmgPayAutoConfigure {
       @Bean
       public PayService payService(YxxmgPayProperties yxxmgPayProperties) {
           ServiceLoader<PayService> serviceLoader = ServiceLoader.load(PayService.class);
           Iterator<PayService> iterator = serviceLoader.iterator();
           PayService payService = null;
           while (iterator.hasNext()) {
               payService = iterator.next();
               if (payService instanceof AlipayServiceImpl && PayMethod.ALIPAY.equals(yxxmgPayProperties.getPayMethod())) {
                   break;
               }
               if (payService instanceof UnionPayServiceImpl && PayMethod.UNION.equals(yxxmgPayProperties.getPayMethod())) {
                   break;
               }
               if (payService instanceof WechatPayServiceImpl && PayMethod.WECHAT.equals(yxxmgPayProperties.getPayMethod())) {
                   break;
               }
               if (Objects.isNull(payService)) {
                   payService = new ErrorPayServiceImpl();
               }
           }
           return payService;
       }
   }
   ```

4. `META-INF/spring.factories`

   ```properties
   org.springframework.boot.autoconfigure.EnableAutoConfiguration=\
     com.yxxmg.pay.config.YxxmgPayAutoConfigure
   ```

5. 引入starter模块

   ```xml
   <dependency>
       <groupId>com.yxxmg</groupId>
       <artifactId>yxxmg-pay-spring-boot-starter</artifactId>
       <version>${project.version}</version>
   </dependency>
   ```

6. 配置文件配置

   ```yaml
   yxxmg:
     pay:
       pay-method: alipay
   ```

7. 测试用例

   ```java
   @SpringBootTest(classes = MybatisPlusSampleApplication.class)
   @RunWith(SpringRunner.class)
   @ActiveProfiles("dev")
   public class MybatisPlusSampleApplicationTest {
       @Resource
       private PayService payService;
   
       @Test
       public void testPayService() {
           String pay = this.payService.pay("iPhone14", Float.parseFloat("6999.99"));
           System.out.println(pay);
       }
   }
   ```

   运行结果

   ![image-20221202111352927](../images/spi测试用例运行结果.png)