package com.tohir.webservices.restfullwebservices.filtering;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.PropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class FilteringController {

    @GetMapping("/filtering")//field2 will have to ignored
    public MappingJacksonValue filtering() {
        SomeBean someBean = new SomeBean("field1", "field2", "field3");

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(someBean);//dynamic filtering
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field1","field3");// to select fields which we want to get them.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }

    @GetMapping("/filtering-list")//field1 will have to ignored
    public MappingJacksonValue filteringList() {
        List<SomeBean> list = Arrays.asList(
                new SomeBean("field1", "field2", "field3"),
                new SomeBean("field4", "field5", "field6")
        );

        MappingJacksonValue mappingJacksonValue = new MappingJacksonValue(list);//dynamic filtering
        PropertyFilter filter = SimpleBeanPropertyFilter.filterOutAllExcept("field2","field3");// to select fields which we want to get them.
        FilterProvider filters = new SimpleFilterProvider().addFilter("SomeBeanFilter",filter);
        mappingJacksonValue.setFilters(filters);

        return mappingJacksonValue;
    }
}
