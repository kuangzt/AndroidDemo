package com.davis.aspect.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zengtao.kuang
 * @Description:
 * @date 2017/1/15 11:21
 * @copyright TCL-MIE
 */
@Retention(RetentionPolicy.CLASS)
@Target({ElementType.CONSTRUCTOR,ElementType.METHOD})
public @interface DebugTrace {
}
