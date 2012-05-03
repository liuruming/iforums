package com.googlecode.iforums.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.googlecode.iforums.web.module.LogicModule;
import com.googlecode.iforums.web.module.ViewModule;
import com.googlecode.iforums.web.module.view.DefaultViewModule;

@Target(ElementType.METHOD) 
@Retention(RetentionPolicy.RUNTIME)
public @interface Modules {
    Class<? extends LogicModule>[] value() ;
    Class<? extends ViewModule> view() default DefaultViewModule.class;
}
