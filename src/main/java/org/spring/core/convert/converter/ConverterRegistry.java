package org.spring.core.convert.converter;

public interface ConverterRegistry {

    void addConverter(Converter<?, ?> converter);

    void addConverterFactory(ConverterFactory<?, ?> convertFactory);

    void addConverter(GenericConverter genericConverter);
}
