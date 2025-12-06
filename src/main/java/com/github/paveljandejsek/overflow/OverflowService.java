package com.github.paveljandejsek.overflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OverflowService {
    public OverflowResult calculateOverflow(int inputFlow, List<Integer> tankCapacities) {
        log.info("Calculating overflow");
        log.atDebug().log("inputFlow: {}, tankCapacities: {}", inputFlow, tankCapacities);



        return new OverflowResult(0, 0);
    }


}
