import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class RestTemplateSupplier {
    public static void main(String[] args) throws InterruptedException {

        System.out.println("Введите название сенсора: ");
        Scanner scanner = new Scanner(System.in);
        String sensor = scanner.nextLine();

        registerSensor(sensor);

        double rangeMin = -49.9;
        double rangeMax = 49.9;
        Random random = new Random();

        for (int i = 0; i < 500; i++) {
            double value = rangeMin+(rangeMax-rangeMin)*random.nextDouble();
            MathContext mathContext = new MathContext(3, RoundingMode.HALF_UP);
            BigDecimal bd = new BigDecimal(value, mathContext);
            sendMeasurement(bd.doubleValue(), random.nextBoolean(),sensor);
        }

        getMeasurements();
    }

    private static void registerSensor(String sensor) {
        final String url = "http://localhost:8080/api/sensors/registration";
        Map<String, Object> data = new HashMap<>();
        data.put("name", sensor);
        makePostRequest(url, data);
    }

    private static void sendMeasurement(double value, boolean raining, String sensor) {
        final String url = "http://localhost:8080/api/measurements/add";
        Map<String, Object> data = new HashMap<>();
        data.put("value", value);
        data.put("raining", raining);
        data.put("sensor", Map.of("name", sensor));
        makePostRequest(url, data);
    }

    private static void getMeasurements() {
        final String url = "http://localhost:8080/api/measurements";
        final RestTemplate restTemplate = new RestTemplate();
        try {
            String forObject = restTemplate.getForObject(url, String.class);
            System.out.println(forObject);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void makePostRequest(String url, Map<String, Object> data) {
        final RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> request = new HttpEntity<>(data, headers);

        try {
            restTemplate.postForObject(url, request, String.class);
        } catch (HttpClientErrorException e) {
            System.out.println(e.getMessage());
        }
    }
}
