package org.spring.beans.context;

import org.spring.beans.BeansException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    void refresh() throws BeansException;
}
