package com.fonseca.algasensors.temperature.monitoring.infrastructure.rabbitmq;

import com.fonseca.algasensors.temperature.monitoring.api.model.TemperatureLogData;
import io.hypersistence.tsid.TSID;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Map;

import static com.fonseca.algasensors.temperature.monitoring.infrastructure.rabbitmq.RabbitMQConfig.QUEUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class RabbitQMListener {

    @RabbitListener(queues = QUEUE)
    @SneakyThrows
    public void handle(@Payload TemperatureLogData temperatureLogData, @Headers Map<String, Object> headers){
        TSID sensorId = temperatureLogData.getSensorId();
        Double temperature = temperatureLogData.getValue();

        log.info("Temperature update: SensorId {} Temp {}", sensorId, temperature);
        log.info("Headers: {}", headers.toString());
        Thread.sleep(Duration.ofSeconds(5));
    }

}
