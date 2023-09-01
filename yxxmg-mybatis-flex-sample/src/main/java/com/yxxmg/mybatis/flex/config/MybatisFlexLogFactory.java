// package com.yxxmg.mybatis.flex.config;
//
// import java.util.UUID;
//
// import com.mybatisflex.core.audit.AuditMessage;
// import com.mybatisflex.core.audit.MessageFactory;
//
/// *****
// *
// * @author : zhaoyan
// * @version : 1.0
// * @description :
// * @since : 2023/8/31
// */
// public class MybatisFlexLogFactory implements MessageFactory {
// @Override
// public AuditMessage create() {
// AuditMessage auditMessage = new AuditMessage();
// auditMessage.setBizId(UUID.randomUUID().toString().replace("-", ""));
// auditMessage.setHostIp("127.0.0.1");
// return auditMessage;
// }
// }
