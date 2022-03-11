package com.fieldju.argus.service;

import com.pi4j.component.button.ButtonPressedListener;
import com.pi4j.component.buzzer.Buzzer;
import com.pi4j.component.light.LED;
import com.pi4j.device.pibrella.Pibrella;
import com.pi4j.device.pibrella.PibrellaLed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@Component
public class PibrellaService {

  private final Buzzer buzzer;
  private final LED red;
  private final LED yellow;
  private final LED green;
  private final AtomicBoolean siren = new AtomicBoolean(false);
  private final ExecutorService executorService = Executors.newCachedThreadPool();
  private final Executor sirenExecutor = CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS, executorService);

  public PibrellaService(Pibrella pibrella) {
    buzzer = pibrella.getBuzzer();
    red = pibrella.getLed(PibrellaLed.RED);
    yellow = pibrella.getLed(PibrellaLed.YELLOW);
    green = pibrella.getLed(PibrellaLed.GREEN);
    var button = pibrella.getButton();
    button.addListener((ButtonPressedListener) event -> {
      var cur = siren.get();
      siren.set(!cur);
    });
    pollAndExecuteSiren();
  }

  private void pollAndExecuteSiren() {
    CompletableFuture.runAsync(() -> {
      while (siren.get()) {
        for (var freq = 500; freq <= 1000; freq = freq + 1) {
          if (freq != 500 && freq % 100 == 0) {
            red.toggle();
          }
          if (!siren.get()) {
            break;
          }
          buzzer.buzz(freq, (int) Duration.ofMillis(5).toMillis());
        }
        for (var freq = 1000; freq >= 500; freq = freq - 1) {
          if (freq != 1000 && freq % 100 == 0) {
            red.toggle();
          }
          if (!siren.get()) {
            break;
          }
          buzzer.buzz(freq, (int) Duration.ofMillis(5).toMillis());
        }
      }
    }).thenRunAsync(this::pollAndExecuteSiren, sirenExecutor);
  }

  public void enableSiren() {
    siren.set(true);
  }

  public void disableSiren() {
    siren.set(false);
  }

  @PostConstruct
  public void after() throws InterruptedException {
    for (var i = 0; i < 3; i++) {
      List.of(green, yellow, red, yellow).forEach(led -> {
        try {
          led.pulseSync(Duration.ofMillis(250).toMillis());
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
      green.pulseSync(Duration.ofMillis(250).toMillis());
    }
  }
}
