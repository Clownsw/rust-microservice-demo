package me.liangdi.cloud.rustmicroserviceconsumer.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author smilex
 * @since 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Message {
    private Integer code;
    private String msg;
}
