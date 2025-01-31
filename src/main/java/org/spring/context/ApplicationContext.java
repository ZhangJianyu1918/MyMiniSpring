package org.spring.context;

import org.spring.core.io.ResourceLoader;
import org.spring.beans.factory.HierarchicalBeanFactory;
import org.spring.beans.factory.ListableBeanFactory;

public interface ApplicationContext extends
        ListableBeanFactory,
        HierarchicalBeanFactory,
        ResourceLoader,
        ApplicationEventPublisher {
}
