package com.github.paveljandejsek.overflow;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class OverflowApplicationTests {

    @MockitoBean
    private InputService inputService;

    @MockitoBean
    private OverflowService overflowService;

    @BeforeEach
    void setup() {
        when(overflowService.calculateOverflow(any(), any())).thenReturn(new OverflowResult(1, 1));
    }

    @Test
    void contextLoads() {
    }

}
