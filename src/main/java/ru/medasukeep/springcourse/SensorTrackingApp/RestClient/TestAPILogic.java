package ru.medasukeep.springcourse.SensorTrackingApp.RestClient;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;
import ru.medasukeep.springcourse.SensorTrackingApp.dto.MeasurementDTO;
import ru.medasukeep.springcourse.SensorTrackingApp.dto.SensorDTO;
import ru.medasukeep.springcourse.SensorTrackingApp.util.GetMeasurementsResponse;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

public class TestAPILogic {
    public static void main(String[] args) throws JsonProcessingException {
        RestTemplate restTemplate = new RestTemplate();

        Map<String, String> map = new HashMap<>();
        map.put("name","newSensor126");
        HttpEntity<Map<String,String>> registerSensorRequest = new HttpEntity<>(map);
        String response = restTemplate.postForObject("http://localhost:8080/sensors",map,String.class);
        System.out.println("Register Sensor Response: " + response);

        for (int i = 0; i <= 10; i++) {
            MeasurementDTO measurementDTO = new MeasurementDTO();
            measurementDTO.setValue((Math.random() * 200) - 100);
            measurementDTO.setRaining(Math.random() > 0.5);
            measurementDTO.setSensor(new SensorDTO());
            measurementDTO.getSensor().setName("newSensor");
            HttpEntity<MeasurementDTO> registerMeasurementRequest = new HttpEntity<>(measurementDTO);
            String measurementResponse = restTemplate.postForObject("http://localhost:8080/measurements/add",measurementDTO,String.class);
            System.out.println("Register Measurement Response: " + measurementResponse);
        }



        GetMeasurementsResponse getMeasurementsResponse = restTemplate.getForObject("http://localhost:8080/measurements",GetMeasurementsResponse.class);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        //String json = ow.writeValueAsString(getMeasurementsResponse);
        //System.out.println("Register Sensor Response: " + json);


        List<Number> values = getMeasurementsResponse.getMeasurementsList().stream().map(measurementDTO -> measurementDTO.getValue()).collect(Collectors.toList());
        List<Number> timeStamps = getMeasurementsResponse.getMeasurementsList().stream().map(measurementDTO -> measurementDTO.getCreatedAt().atZone(ZoneId.of("Europe/Moscow")).toInstant().toEpochMilli()).collect(Collectors.toList());
        System.out.println(values);
        System.out.println(timeStamps);
        // Create Chart
        XYChart chart = QuickChart.getChart("Sample Chart", "X", "Y", "y(x)", timeStamps, values);

        // Show it
        new SwingWrapper(chart).displayChart();
    }
}
