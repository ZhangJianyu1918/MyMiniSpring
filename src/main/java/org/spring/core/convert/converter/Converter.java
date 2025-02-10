package org.spring.core.convert.converter;

public interface Converter<S, T> {

    T convert(S source);
}
