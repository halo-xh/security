package com.xh.rest.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by Xiao Hong on 2020-12-11
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginVM {
    
    private String username;
    private char[] password;
}
