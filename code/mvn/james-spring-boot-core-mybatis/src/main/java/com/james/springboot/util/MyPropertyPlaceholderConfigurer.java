package com.james.springboot.util;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * author: yang
 * datetime: 2020/7/22 15:17
 */

public class MyPropertyPlaceholderConfigurer extends PropertyPlaceholderConfigurer implements ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader;

    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        List<Resource> resources = new ArrayList();
        super.setLocations(resources.toArray(new Resource[resources.size()]));
    }

    @Override
    protected String resolvePlaceholder(String placeholder, Properties props, int systemPropertiesMode) {
        String value = super.resolvePlaceholder(placeholder,props,systemPropertiesMode);
        return value.trim();
    }

}
