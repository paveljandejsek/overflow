package com.github.paveljandejsek.overflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Scanner;
import java.util.stream.IntStream;

import static java.lang.System.exit;

@Slf4j
@Service
public class InputService {

    public FlowSpecification getFlowSpecification() {
        Scanner scanner = new Scanner(System.in);
        String firstLineString = scanner.nextLine();
        log.atDebug().log("First line string: {}", firstLineString);
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

        return new FlowSpecification(numberOfTanks, inputFlow);
    }

    public List<Integer> getTankCapacities(FlowSpecification flowSpecification) {
        Scanner scanner = new Scanner(System.in);

        return IntStream.range(0, flowSpecification.numberOfTanks())
                .mapToObj(_ -> scanner.nextLine())
                .map(InputService::parseCapacityInput)
                .toList();
    }

    private static Integer parseCapacityInput(String capacityInput) {
        log.atDebug().log("Capacity input: {}", capacityInput);
        try {
            return Integer.parseInt(capacityInput);
        } catch (NumberFormatException e) {
            System.err.println("Invalid capacity input!");
            exit(2);
        }
        return null;
    }

}
