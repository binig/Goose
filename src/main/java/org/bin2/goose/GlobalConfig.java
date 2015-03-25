package org.bin2.goose;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc
@ComponentScan(basePackages = {
        "org.bin2.goose.dao",
        "org.bin2.goose.service",
        "org.bin2.goose.websocket",
        "org.bin2.goose.signaling",
})
@ImportResource({
})
@Configuration
public class GlobalConfig extends WebMvcConfigurerAdapter {
}