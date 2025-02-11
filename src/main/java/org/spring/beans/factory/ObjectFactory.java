package org.spring.beans.factory;

import org.spring.beans.BeansException;

public interface ObjectFactory<T> {

    T getObject() throws BeansException;
}
