package com.cat.test.web.entity;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User {

    private long id;

    private String name;

    private int age;
}
