package org.spring.beans.context;

import org.spring.beans.core.io.ResourceLoader;
import org.spring.beans.factory.HierarchicalBeanFactory;
import org.spring.beans.factory.ListableBeanFactory;

public interface ApplicationContext extends ListableBeanFactory, HierarchicalBeanFactory, ResourceLoader {
}
