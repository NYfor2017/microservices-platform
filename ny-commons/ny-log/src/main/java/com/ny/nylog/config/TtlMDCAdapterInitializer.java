package com.ny.nylog.config;

import com.ny.nylog.org.slf4j.TtlMDCAdapter;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @author N.Y
 * @date 2020-03-27 11:40
 */
public class TtlMDCAdapterInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
        //加载TtlMDCAdapter实例
        TtlMDCAdapter.getInstance();
    }
}
