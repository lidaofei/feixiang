package com.feixiang.tdd;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author lidaofei
 * @date 2019/12/23 23:56
 */
@Data
@AllArgsConstructor
public class User {
    private Long id;

    private String name;

    private Integer age;
}
