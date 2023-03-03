package tddmicroexercises.solidtelemetrysystem;

import tddmicroexercises.telemetrysystem.TelemetryClient;

public class TelemetryDiagnosticControlsImpl implements ITelemetryDiagnosticControls
{

    private final String DiagnosticChannelConnectionString = "*111#";

    private final ITelemetryClientConnection telemetryClient;

    private final  ITelemetryClientTransaction telemetryClientMessageSender;
    private String diagnosticInfo = "";

    public TelemetryDiagnosticControlsImpl(ITelemetryClientConnection telemetryClient, ITelemetryClientTransaction telemetryClientMessageSender) {
        this.telemetryClient = telemetryClient;
        this.telemetryClientMessageSender = telemetryClientMessageSender;
    }


    //Getters & Setters

    public String getDiagnosticInfo(){
        return diagnosticInfo;
    }

    public void setDiagnosticInfo(String diagnosticInfo){
        this.diagnosticInfo = diagnosticInfo;
    }



    public void checkTransmission(Integer retryLimit)throws Exception {

        diagnosticInfo = "";

            telemetryClient.disconnect();

            int retryLeft = retryLimit;
            while (telemetryClient.getOnlineStatus() == false && retryLeft > 0)
            {
                telemetryClient.connect(DiagnosticChannelConnectionString);
                retryLeft -= 1;
            }

            if(telemetryClient.getOnlineStatus() == false)
            {
                throw new Exception("Unable to connect.");
            }

        telemetryClientMessageSender.send(TelemetryClient.DIAGNOSTIC_MESSAGE);
            diagnosticInfo = telemetryClientMessageSender.receive();
    }
}
