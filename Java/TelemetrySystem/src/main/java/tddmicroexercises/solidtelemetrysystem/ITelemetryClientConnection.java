package tddmicroexercises.solidtelemetrysystem;

public interface ITelemetryClientConnection {

    void connect(String telemetryServerConnectionString);

    void disconnect();

    boolean getOnlineStatus();
}
