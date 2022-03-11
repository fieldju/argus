package com.fieldju.argus.config;

import com.pi4j.device.pibrella.Pibrella;
import com.pi4j.device.pibrella.impl.PibrellaDevice;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Slf4j
@EnableAsync
@Configuration
@ComponentScan("com.fieldju.argus")
@EnableAutoConfiguration
public class AppConfiguration {

  @Bean
  public Pibrella pibrella() {
    return new PibrellaDevice();
  }

}
