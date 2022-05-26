package com.viloveul.module.management.pojo;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class CheckPermission implements Serializable {

    private String resource;

    private String operation;

    private String object;

}
