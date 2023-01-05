package com.util;
import lombok.Data;

import java.io.Serializable;

import java.util.Map;

/**
 *  A return Util to pages
 * @param <T> return data type
 */
@Data
public class Resp<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private final static String SUCCESS_CODE = "200";

    /**
     * The status returned to the page
     */
    private String status;

    /**
     * Message returned to the page
     */
    private String message;

    /**
     * Return data to the page
     */
    private T data;

    /**
     *  Other data that needs to be returned to the page
     */
    private Map<String,Object> rtnMap;


}