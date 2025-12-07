package com.github.paveljandejsek.overflow;

public record OverflowResult(int secondsToLastOverflow, int secondsToAllFull) {

    @Override
    public String toString() {
        return String.format("%d %d", secondsToLastOverflow(), secondsToAllFull());
    }
}
