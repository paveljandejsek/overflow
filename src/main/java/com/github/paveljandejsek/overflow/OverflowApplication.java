package com.github.paveljandejsek.overflow;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.System.exit;

@RequiredArgsConstructor
@SpringBootApplication
public class OverflowApplication implements CommandLineRunner {

    private final OverflowService overflowService;

    public static void main(String[] args) {
        SpringApplication.run(OverflowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String firstLineString = scanner.nextLine();
        if (!firstLineString.contains(" ")) {
            System.err.println("Invalid first line");
            exit(1);
        }

        String[] firstLineStrings = firstLineString.split(" ");
        int numberOfTanks = 0;
        int inputFlow = 0;
        try {
            numberOfTanks = Integer.parseInt(firstLineStrings[0]);
            inputFlow = Integer.parseInt(firstLineStrings[1]);
        } catch (NumberFormatException e) {
            System.err.println("Invalid first line");
            exit(1);
        }

        List<Integer> tankCapacities = IntStream.range(0, numberOfTanks)
                .mapToObj(_ -> scanner.nextLine())
                .map(OverflowApplication::parseCapacityInput)
                .toList();

        OverflowResult overflowResult = overflowService.calculateOverflow(inputFlow, tankCapacities);

        String resultOutput = String.format("%d %d", overflowResult.secondsToLastOverflow(), overflowResult.secondsToAllFull());
        System.out.println(resultOutput);
    }

    private static Integer parseCapacityInput(String capacityInput) {
        try {
            return Integer.parseInt(capacityInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid capacity input!");
            exit(2);
        }
        return null;
    }

}
