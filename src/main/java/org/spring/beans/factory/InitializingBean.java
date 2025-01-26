package org.spring.beans.factory;

public interface InitializingBean {

    void afterPropertiesSet() throws Exception;
}
