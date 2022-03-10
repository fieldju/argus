package com.fieldju.commandpost;

import com.fieldju.commandpost.config.AppConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;

import java.util.Map;

public class Main {
    public static void main(String[] args) throws Exception {
        var appCtx = new SpringApplicationBuilder()
          .properties(
            Map.of( // spotless:off
              "spring.application.name", "command-post",
              "spring.config.name", "command-post",
              "spring.profiles.active", "pibrella"
            )) // spotless:on
          .sources(AppConfiguration.class)
          .run(args);
    }
}
