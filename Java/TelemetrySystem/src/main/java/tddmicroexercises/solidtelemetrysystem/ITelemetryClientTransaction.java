package tddmicroexercises.solidtelemetrysystem;

public interface ITelemetryClientTransaction {
    void send(String message);

    String receive();
}
