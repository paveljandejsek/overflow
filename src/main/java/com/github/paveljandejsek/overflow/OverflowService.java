package com.github.paveljandejsek.overflow;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OverflowService {
    public OverflowResult calculateOverflow(FlowSpecification flowSpecification, List<Integer> tankCapacities) {
        log.atInfo().log("Calculating overflow");
        log.atDebug().log("flow specification: {}, tankCapacities: {}", flowSpecification, tankCapacities);

        int inputFlow = flowSpecification.inputFlow();
        double allFullTime = getMaxFullTime(tankCapacities, inputFlow);

        int lastSmallestTankIndex = getLastSmallestTankIndex(tankCapacities);
        log.atDebug().log("smallest last tank index: {}", lastSmallestTankIndex);
        List<Integer> lastTankRelevantSizes = tankCapacities.subList(lastSmallestTankIndex, tankCapacities.size());
        double lastOverflow = getMaxFullTime(lastTankRelevantSizes, inputFlow);

        int lastOverflowRounded = (int)Math.floor(lastOverflow);
        int allFullRounded = (int)Math.floor(allFullTime);

        log.atInfo().log("Last overflow rounded: {}", lastOverflowRounded);
        log.atInfo().log("All overflow rounded: {}", allFullRounded);
        return new OverflowResult(lastOverflowRounded, allFullRounded);
    }

    private double getMaxFullTime(List<Integer> tankCapacities, int inputFlow) {
        // time of previous tank * flow -> what it gets before overflow starts
        // ((tank index+1)*flow) * time of this tank - overflow of all the previous tanks for the time after
        // capacity of tank = (time of previous * flow) + ((tank index+1)*flow)* time of this tank
        // time of this tank = (capacity of this tank + ((tank index+1)*flow)* time of previous tank) / ((tank index+1)*flow)

        double previousTankTime = (double) tankCapacities.getFirst() / inputFlow;
        double maxFullTime = previousTankTime;
        log.atDebug().log("tankIndex: {}, tankFillTime: {}", 0, previousTankTime);

        for (int tankIndex = 1; tankIndex < tankCapacities.size(); tankIndex++) {
            double tankFillTime = ((double) tankCapacities.get(tankIndex) + (double) tankIndex * inputFlow * previousTankTime) / ((double) (tankIndex + 1) * inputFlow);
            log.atDebug().log("tankIndex: {}, tankFillTime: {}", tankIndex, tankFillTime);
            if (tankFillTime > maxFullTime) maxFullTime = tankFillTime;
            previousTankTime = tankFillTime;
        }

        return (long) Math.floor(maxFullTime);
    }

    private int getLastSmallestTankIndex(List<Integer> tankCapacities) {
        int smallestIndex = tankCapacities.size() - 1;
        int smallestSize = tankCapacities.get(smallestIndex);
        for (int i = tankCapacities.size() - 2; i >= 0; i--) {
            if (tankCapacities.get(i) < smallestSize) {
                smallestSize = tankCapacities.get(i);
                smallestIndex = i;
            } else {
                break;
            }
        }
        return smallestIndex;
    }

}
