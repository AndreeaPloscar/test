package sensor.ui;

import common.dto.SensorDto;
import common.service.ISensorService;
import sensor.service.SensorServiceClient;

import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ExecutionException;

public class Ui {
    private final ISensorService sensorServiceClient;
    private final Scanner scanner;

    public Ui(ISensorService sensorServiceClient) {
        this.sensorServiceClient = sensorServiceClient;
        scanner = new Scanner(System.in);
    }

    public void run(){

        System.out.println("ID:");
        String idString = scanner.nextLine();
        Integer id = Integer.parseInt(idString);

        System.out.println("Name:");
        String name = scanner.nextLine();


        System.out.println("Lower Bound:");
        String lowerString = scanner.nextLine();
        int lower = Integer.parseInt(lowerString);


        System.out.println("Upper Bound:");
        String upperString = scanner.nextLine();
        int upper = Integer.parseInt(upperString);

        var generator = new Random();

        while (true) {
            double measurement = generator.nextDouble() * (upper - lower) + lower;

            System.out.println("Sending measurement " + measurement);

            var dto = new SensorDto(id, name, measurement);
            try {
                sensorServiceClient.sendMeasurements(dto).get();
                System.out.println("Successfully added measurement!");
            } catch (InterruptedException | ExecutionException e) {
                System.out.println("Could not add measurement!");
                System.out.println(e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }

            try {
                Thread.sleep((new Random().nextInt(3) + 1) * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
