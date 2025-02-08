package org.spring.beans.factory;

import org.spring.beans.BeansException;
import org.spring.beans.PropertyValue;
import org.spring.beans.PropertyValues;
import org.spring.beans.factory.config.BeanDefinition;
import org.spring.beans.factory.config.BeanFactoryPostProcessor;
import org.spring.core.io.DefaultResourceLoader;
import org.spring.core.io.Resource;

import java.io.IOException;
import java.util.Properties;

public class PropertyPlaceholderConfigurer implements BeanFactoryPostProcessor {

    public static final String PLACEHOLDER_PREFIX = "${";

    public static final String PLACEHOLDER_SUFFIX = "}";

    private String location;


    /**
     * 在beanDefinition加载完成之后，bean实例化之前，提供修改beanDefinition属性的机制
     *
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        // 加载配置文件
        Properties properties = loadProperties();
        // 属性值替换占位符
        processProperties(beanFactory, properties);

    }


    /**
     * 加载配置文件
     * @return
     */
    private Properties loadProperties() {
        try {
            DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
            Resource resource = resourceLoader.getResource(location);
            Properties properties = new Properties();
            properties.load(resource.getInputStream());
            return properties;
        } catch (IOException e) {
            throw new BeansException("Could not load properties", e);
        }
    }

    /**
     * 属性值替换占位符
     * @param beanFactory
     * @param properties
     * @throws BeansException
     */
    private void processProperties(ConfigurableListableBeanFactory beanFactory, Properties properties) throws BeansException{
        String[] beanDefinitionNames = beanFactory.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition(beanDefinitionName);
            resolvePropertyValue(beanDefinition, properties);
        }
    }

    private void resolvePropertyValue(BeanDefinition beanDefinition, Properties properties) {
        PropertyValues propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues.getPropertyValues()) {
            Object value = propertyValue.getValue();
            if (value instanceof String) {
                // TODO 目前仅支持一个占位符的格式
                String stringValue = (String) value;
                StringBuffer stringBuffer = new StringBuffer(stringValue);
                int startIndex = stringValue.indexOf(PLACEHOLDER_PREFIX);
                int endIndex = stringValue.indexOf(PLACEHOLDER_SUFFIX);
                if (startIndex != -1 && endIndex != -1 && startIndex < endIndex) {
                    String propertyKey = stringValue.substring(startIndex + 2, endIndex);
                    String property = properties.getProperty(propertyKey);
                    stringBuffer.replace(startIndex, endIndex + 1, property);
                    propertyValues.addPropertyValue(new PropertyValue(propertyValue.getName(), stringBuffer.toString()));
                }
            }
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
