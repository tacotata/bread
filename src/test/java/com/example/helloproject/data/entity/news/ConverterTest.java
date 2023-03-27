package com.example.helloproject.data.entity.news;

import com.example.helloproject.utils.StringToNewsTypeConverter;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class ConverterTest {

    @Test
   public void stringToNewsTypeConvertTest() {
        StringToNewsTypeConverter converter = new StringToNewsTypeConverter();
        NewsType notice = converter.convert("notice");
        assertEquals(NewsType.NOTICE, notice);
        assertThat(NewsType.valueOfLabel("notice")).isEqualTo(NewsType.NOTICE);
    }
}
