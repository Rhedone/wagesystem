package com.solinor.wagesystem.transformation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.solinor.wagesystem.model.CalculatedWage;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Map;

/**
 * Created by yolan
 */
@Component
public class WagesToJsonTransformer {
    private ObjectMapper mapper;

    public WagesToJsonTransformer() {
        mapper = new ObjectMapper();
    }

    public String transform(Map<Integer, CalculatedWage> wages) throws JsonProcessingException {
        Collection<CalculatedWage> values = wages.values();
        String s = mapper.writeValueAsString(values);
        
        return s;
    }
}
