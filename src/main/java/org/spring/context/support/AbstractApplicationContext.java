package org.spring.context.support;

import org.spring.beans.BeansException;
import org.spring.context.ApplicationEvent;
import org.spring.context.ApplicationListener;
import org.spring.context.ConfigurableApplicationContext;
import org.spring.context.event.ApplicationEventMulticaster;
import org.spring.context.event.ContextClosedEvent;
import org.spring.context.event.ContextRefreshedEvent;
import org.spring.context.event.SimpleApplicationEventMulticaster;
import org.spring.core.io.DefaultResourceLoader;
import org.spring.beans.factory.ConfigurableListableBeanFactory;
import org.spring.beans.factory.config.BeanFactoryPostProcessor;
import org.spring.beans.factory.config.BeanPostProcessor;

import java.util.Collection;
import java.util.Map;

public abstract class AbstractApplicationContext extends DefaultResourceLoader
        implements ConfigurableApplicationContext {

    public static final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    private ApplicationEventMulticaster applicationEventMulticaster;

    @Override
    public void refresh() throws BeansException {
        // 创建BeanFactory，并加载BeanDefinition
        refreshBeanFactory();
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        // 添加ApplicationContextAwareProcessor，
        // 让继承自ApplicationContextAware的bean能感知bean
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        // 在bean实例化之前，执行BeanFactoryPostProcessor
        invokeBeanFactoryPostProcessors(beanFactory);

        // BeanPostProcessor需要提前与其他bean实例化之前注册
        registerBeanPostProcessors(beanFactory);

        // 初始化事件发布者
        initApplicationEventMulticaster();

        // 注册事件监听器
        registerListeners();

        // 提前实例化单例bean
        beanFactory.preInstantiateSingletons();

        // 发布容器刷新完成事件
        finishRefresh();
    }

    protected void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanPostProcessor> beanPostProcessorMap = beanFactory.getBeansOfType(BeanPostProcessor.class);
        for (BeanPostProcessor beanPostProcessor : beanPostProcessorMap.values()) {
            beanFactory.addBeanPostProcessor(beanPostProcessor);
        }
    }

    /**
     * 在bean实例化之前，执行BeanFactoryPostProcessor
     * @param beanFactory
     */
    protected void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory) {
        Map<String, BeanFactoryPostProcessor> beanFactoryPostProcessorMap = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        for (BeanFactoryPostProcessor beanFactoryPostProcessor : beanFactoryPostProcessorMap.values()) {
            beanFactoryPostProcessor.postProcessBeanFactory(beanFactory);
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();

    /**
     * 创建BeanFactory，并加载BeanDefinition
     * @throws BeansException
     */
    protected abstract void refreshBeanFactory() throws BeansException;

    @Override
    public Object getBean(String name) throws BeansException {
        return getBeanFactory().getBean(name);
    }

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(name, requiredType);
    }

    @Override
    public <T> T getBean(Class<T> requiredType) throws BeansException {
        return getBeanFactory().getBean(requiredType);
    }

    /**
     * 返回指定类型的所有bean
     *
     * @param type
     * @return
     * @throws BeansException
     */
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        return getBeanFactory().getBeansOfType(type);
    }

    /**
     * 返回定义的所有bean名称
     *
     * @return
     */
    @Override
    public String[] getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    public void close() {
        doClose();
    }

    protected void doClose() {
        // 发布容器关闭事件
        publishEvent(new ContextClosedEvent(this));

        // 执行单例bean的销毁方法
        destroyBeans();
    }

    @Override
    public void publishEvent(ApplicationEvent event) {
        applicationEventMulticaster.multicastEvent(event);
    }

    /**
     * 发布容器刷洗完成事件
     */
    public void finishRefresh() {
        publishEvent(new ContextRefreshedEvent(this));
    }

    /**
     * 注册事件监听器
     */
    public void registerListeners() {
        Collection<ApplicationListener> applicationListeners = getBeansOfType(ApplicationListener.class).values();
        for (ApplicationListener applicationListener : applicationListeners) {
            applicationEventMulticaster.addApplicationListener(applicationListener);
        }
    }

    /**
     * 初始化事件发布者
     */
    public void initApplicationEventMulticaster() {
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster(beanFactory);
        beanFactory.addSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);
    }


    protected void destroyBeans() {
        getBeanFactory().destroySingletons();
    }

    public void registerShutdownHook() {
        Thread shutdownHook = new Thread() {
            @Override
            public void run() {
                doClose();
            }
        };
        Runtime.getRuntime().addShutdownHook(shutdownHook);
    }
}
