package com.github.paveljandejsek.overflow;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class OverflowServiceTest {

    private final OverflowService overflowService = new OverflowService();

    @Test
    void calculateExample1() {
        FlowSpecification flowSpecification = new FlowSpecification(4, 1);
        List<Integer> tankCapacities = List.of(30, 3, 7, 20);

        Assertions.assertThat(overflowService.calculateOverflow(flowSpecification, tankCapacities))
                .as("Wrong response for Example 1")
                .isEqualTo(new OverflowResult(10, 30));
    }

    @Test
    void calculateExample2() {
        FlowSpecification flowSpecification = new FlowSpecification(2, 2);
        List<Integer> tankCapacities = List.of(4, 6);

        Assertions.assertThat(overflowService.calculateOverflow(flowSpecification, tankCapacities))
                .as("Wrong response for Example 2")
                .isEqualTo(new OverflowResult(2, 2));
    }

    @Test
    void calculateExample3() {
        FlowSpecification flowSpecification = new FlowSpecification(10, 7);
        List<Integer> tankCapacities = List.of(
                100000000, 99999999, 10000000,
                1000000, 900000, 90000,
                9000, 800, 80, 777
        );

        Assertions.assertThat(overflowService.calculateOverflow(flowSpecification, tankCapacities))
                .as("Wrong response for Example 3")
                .isEqualTo(new OverflowResult(61, 14285714));
    }

}