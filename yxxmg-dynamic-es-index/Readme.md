# elasticsearch动态索引

### ik_max_word 和 ik_smart 区别

ik_max_word: 会将文本做最细粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,中华人民,中华,华人,人民共和国,人民,人,民,共和国,共和,和,国国,国歌”，会穷尽各种可能的组合，适合
Term Query；
ik_smart: 会做最粗粒度的拆分，比如会将“中华人民共和国国歌”拆分为“中华人民共和国,国歌”，适合 Phrase 查询。

### TEXT、KEYWORD区别

text类型: 存储数据时候，会自动分词，并生成索引
1:支持分词，全文检索,支持模糊、精确查询,不支持聚合,排序操作;【比如：对于‘佟永硕’，ik分词器的smart分词会自动将其分成佟、永、硕三个字符进行建立索引，所以单字符搜索可以搜索到，而比如‘永硕’则搜索不到】
2:最大支持的字符长度无限制,适合大字段存储；
使用场景：
存储全文搜索数据, 例如: 邮箱内容、地址、代码块、博客文章内容等。
默认结合standard analyzer(标准解析器)对文本进行分词、倒排索引。
默认结合标准分析器进行词命中、词频相关度打分。

keyword类型: 存储数据时候，不会分词建立索引
1:不进行分词，直接索引,支持模糊、支持精确匹配，支持聚合、排序操作。【依据此特点，可以使用keyword类型+wildcardQuery（通配查询）实现类似sql的like查询（模糊搜索）】
2:keyword类型的最大支持的长度为0——32766个UTF-8类型的字符,可以通过设置ignore_above指定自持字符长度，超过给定长度后的数据将不被索引，无法通过term精确匹配检索返回结果。
使用场景：
存储邮箱号码、url、name、title，手机号码、主机名、状态码、邮政编码、标签、年龄、性别等数据。
用于筛选数据(例如: select * from x where status=‘open’)、排序、聚合(统计)。
直接将完整的文本保存到倒排索引中。

### analyzer和search_analyzer区别

```java
@Field(type = FieldType.text, analyzer = “ik_max_word”, searchAnalyzer = “ik_smart”)
```

analyzer 是插入文档时，将text类型的字段做分词然后插入倒排索引，
search_analyzer 是在查询时，先对要查询的text类型的输入做分词，再去倒排索引搜索
如果想要让 索引 和 查询 时使用不同的分词器，ElasticSearch也是能支持的，只需要在字段上加上search_analyzer参数
在索引时，只会去看字段有没有定义analyzer，有定义的话就用定义的，没定义就用ES预设的
在查询时，会先去看字段有没有定义search_analyzer，如果没有定义，就去看有没有analyzer，再没有定义，才会去使用ES预设的
