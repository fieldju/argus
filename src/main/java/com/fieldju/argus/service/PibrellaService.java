package com.fieldju.argus.service;

import com.pi4j.component.buzzer.Buzzer;
import com.pi4j.component.light.LED;
import com.pi4j.device.pibrella.Pibrella;
import com.pi4j.device.pibrella.PibrellaLed;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Slf4j
@Component
public class PibrellaService {

  private final Buzzer buzzer;
  private final LED red;
  private final LED yellow;
  private final LED green;

  public PibrellaService(Pibrella pibrella) {
    buzzer = pibrella.getBuzzer();
    red = pibrella.getLed(PibrellaLed.RED);
    yellow = pibrella.getLed(PibrellaLed.YELLOW);
    green = pibrella.getLed(PibrellaLed.GREEN);
  }

  @PostConstruct
  public void after() {
    green.on();
  }

}
