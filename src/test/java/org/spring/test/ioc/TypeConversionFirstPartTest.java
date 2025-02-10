package org.spring.test.ioc;

import org.junit.Test;
import org.spring.core.convert.converter.Converter;
import org.spring.core.convert.support.GenericConversionService;
import org.spring.core.convert.support.StringToNumberConverterFactory;
import org.spring.test.common.StringToBooleanConverter;
import org.spring.test.common.StringToIntegerConverter;

public class TypeConversionFirstPartTest {

    @Test
    public void testStringToIntegerConverter() throws Exception {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer num = converter.convert("1234");
        System.out.println(num == 1234);
    }

    @Test
    public void testStringToNumberConverterFactory() throws Exception {
        StringToNumberConverterFactory converterFactory = new StringToNumberConverterFactory();
        Converter<String, Integer> stringToIntegerConverter = converterFactory.getConverter(Integer.class);
        Integer intNum = stringToIntegerConverter.convert("1234");
        System.out.println(intNum == 1234);

        Converter<String, Long> stringToLongConverter = converterFactory.getConverter(Long.class);
        Long longNum = stringToLongConverter.convert("3121");
        System.out.println(longNum == 3121);
        System.out.println(longNum == 3121L);
    }

    @Test
    public void testGenericConversionService() throws Exception {
        GenericConversionService conversionService = new GenericConversionService();
        conversionService.addConverter(new StringToIntegerConverter());

        Integer intNum = conversionService.convert("1233", Integer.class);
        System.out.println(conversionService.canConvert(String.class, Integer.class));

        conversionService.addConverterFactory(new StringToNumberConverterFactory());
        System.out.println(conversionService.canConvert(String.class, Long.class));
        Long longNum = conversionService.convert("1233", Long.class);
        System.out.println(longNum == 1233L);

        conversionService.addConverter(new StringToBooleanConverter());
        System.out.println(conversionService.canConvert(String.class, Boolean.class));
        Boolean flag = conversionService.convert("true", Boolean.class);
        System.out.println(flag == true);
    }
}
