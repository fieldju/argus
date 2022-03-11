package com.fieldju.argus;

import com.fieldju.argus.config.AppConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        var appCtx = new SpringApplicationBuilder()
          .properties(
            Map.of( // spotless:off
              "spring.application.name", "argus",
              "spring.config.name", "arugs",
              "spring.profiles.active", "pibrella"
            )) // spotless:on
          .sources(AppConfiguration.class)
          .run(args);
    }
}
