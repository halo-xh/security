package com.xh.common;

import java.io.Serializable;

/**
 * Created by Xiao Hong on 2020-12-11
 */
public enum ResponseStatus implements Serializable {
    
    SUCCESS(200, "success"),
    ERROR(500, "error");
    
    ResponseStatus(Integer code, String msg) {
    }
    
}
