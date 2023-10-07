package com.yxxmg.rabbitmq.dto;

import java.io.Serializable;

import com.yxxmg.rabbitmq.domain.BasicMessage;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author : yxxmg
 * @version : 1.0
 * @description :
 * @since : 2023/10/7
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class SmsDTO extends BasicMessage implements Serializable {}
