package org.bin2.meerkat;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {
        "org.bin2.meerkat.dao",
        "org.bin2.meerkat.service",
        "org.bin2.meerkat.websocket",
})
@ImportResource({
})
@Configuration
public class GlobalConfig extends WebMvcConfigurerAdapter {
}