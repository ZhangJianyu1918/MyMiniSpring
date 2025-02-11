package org.spring.context.support;

import org.spring.beans.factory.FactoryBean;
import org.spring.beans.factory.InitializingBean;
import org.spring.core.convert.ConversionService;
import org.spring.core.convert.converter.Converter;
import org.spring.core.convert.converter.ConverterFactory;
import org.spring.core.convert.converter.ConverterRegistry;
import org.spring.core.convert.converter.GenericConverter;
import org.spring.core.convert.support.DefaultConversionService;
import org.spring.core.convert.support.GenericConversionService;

import java.util.Set;

public class ConversionServiceFactoryBean implements FactoryBean<ConversionService>, InitializingBean {

    private Set<?> converters;

    private GenericConversionService conversionService;

    @Override
    public ConversionService getObject() throws Exception {
        return conversionService;
    }

    public void setConverters(Set<?> converters) {
        this.converters = converters;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        conversionService = new DefaultConversionService();
        registerConverters(converters, conversionService);
    }

    private void registerConverters(Set<?> converters, ConverterRegistry conversionService) {
        if (converters != null) {
            for (Object converter : converters) {
                if (converters instanceof GenericConverter) {
                    conversionService.addConverter((GenericConverter) converter);
                } else if (converter instanceof Converter<?, ?>) {
                    conversionService.addConverter((Converter<?, ?>) converter);
                } else if (converter instanceof ConverterFactory<?, ?>) {
                    conversionService.addConverterFactory((ConverterFactory<?, ?>) converter);
                } else {
                    throw new IllegalArgumentException("Each converter object " +
                            "implement on of the Converter, ConverterFactory, " +
                            "or GenericConverter interfaces");
                }
            }
        }
    }


}
