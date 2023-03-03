package tddmicroexercises.telemetrysystem;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import tddmicroexercises.solidtelemetrysystem.*;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TelemetryDiagnosticControlsImplTest
{


    final String info= "LAST TX rate................ 100 MBPS\r\n"
            + "HIGHEST TX rate............. 100 MBPS\r\n"
            + "LAST RX rate................ 100 MBPS\r\n"
            + "HIGHEST RX rate............. 100 MBPS\r\n"
            + "BIT RATE.................... 100000000\r\n"
            + "WORD LEN.................... 16\r\n"
            + "WORD/FRAME.................. 511\r\n"
            + "BITS/FRAME.................. 8192\r\n"
            + "MODULATION TYPE............. PCM/FM\r\n"
            + "TX Digital Los.............. 0.75\r\n"
            + "RX Digital Los.............. 0.10\r\n"
            + "BEP Test.................... -5\r\n"
            + "Local Rtrn Count............ 00\r\n"
            + "Remote Rtrn Count........... 00";;
	@Test
    public void CheckTransmission_should_send_a_diagnostic_message_and_receive_a_status_message_response()
    {
         final ITelemetryClientConnection telemetryClient = new TelemetryClientImpl();

         final ITelemetryClientTransaction telemetryClientMessageSender = new TelemetryClientMessageSenderImpl();
        TelemetryDiagnosticControlsImpl telemetryDiagnosticControls = new TelemetryDiagnosticControlsImpl(telemetryClient, telemetryClientMessageSender);
        try{
            telemetryDiagnosticControls.checkTransmission(3);
            System.out.println(telemetryDiagnosticControls.getDiagnosticInfo());
            if(telemetryDiagnosticControls.getDiagnosticInfo().length() == 0){
                Assertions.fail("Response Empty");
            }
            assertEquals(telemetryDiagnosticControls.getDiagnosticInfo(),info);
        }catch(Exception e){
            System.out.println(e);
        }
    }


    @Test
    public void CheckTransmission_should_throw_exception() throws Exception{
        TelemetryClientImpl telemetryClient = new TelemetryClientImpl();
        final ITelemetryClientConnection telemetryClientImpl = new TelemetryClientImpl();

        final ITelemetryClientTransaction telemetryClientMessageSender = new TelemetryClientMessageSenderImpl();

        String errorMessage ="Unable to connect.";
        TelemetryDiagnosticControlsImpl telemetryDiagnosticControls = new TelemetryDiagnosticControlsImpl(telemetryClientImpl, telemetryClientMessageSender);
        try{
            telemetryClient.disconnect();
            telemetryDiagnosticControls.checkTransmission(0);
            if(telemetryDiagnosticControls.getDiagnosticInfo().length() == 0){
                Assertions.fail("Response Empty");
            }

        }catch(Exception e){
            System.out.println(e);
            Assertions.assertEquals(e.getMessage(),errorMessage);
        }
    }

}
