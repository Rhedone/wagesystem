package com.solinor.wagesystem.transformation;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Created by yolan
 */
@Component
public class WagesToJsonTransformer {

    public String transform(Map<String, BigDecimal> wages) {
        return "blap";
    }
}
