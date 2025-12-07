package com.github.paveljandejsek.overflow;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@SpringBootApplication
public class OverflowApplication implements CommandLineRunner {

    private final OverflowService overflowService;
    private final InputService inputService;

    public static void main(String[] args) {
        SpringApplication.run(OverflowApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        log.atInfo().log("Starting Overflow Application");
        FlowSpecification flowSpecification = inputService.getFlowSpecification();
        List<Integer> tankCapacities = inputService.getTankCapacities(flowSpecification);
        OverflowResult overflowResult = overflowService.calculateOverflow(flowSpecification, tankCapacities);

        System.out.println(overflowResult);
    }

}
