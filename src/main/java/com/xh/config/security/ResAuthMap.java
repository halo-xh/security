package com.xh.config.security;

import lombok.*;

import java.io.Serializable;

/**
 * eg. [POST]/api/test1
 *
 * Created by Xiao Hong on 2020-12-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ResAuthMap implements Serializable {

    private Integer rid;// resource id
    private String method; // request method
    private String path; // request api
    private String authority; // role id
}
