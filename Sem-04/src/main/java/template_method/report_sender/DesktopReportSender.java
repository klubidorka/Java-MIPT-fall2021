package template_method.report_sender;

import java.util.Random;

import static template_method.report_sender.LatencySimulator.simulateDeviceLatency;
import static template_method.report_sender.LatencySimulator.simulateNetworkLatency;

public class DesktopReportSender extends ReportSender {
    public DesktopReportSender(String login, String password) {
        super(login, password);
    }

    @Override
    protected boolean logIn(String login, String password) {
        Random random = new Random();
        boolean success = random.nextBoolean();
        System.out.println("Logging in");
        simulateNetworkLatency();
        if (success) {
            System.out.println("Successfully logged in");
            System.out.println("User: " + login + "\nPassword: " + "*".repeat(password.length()) + "\n");
            return true;
        }
        System.out.println("Failed to log in");
        return false;
    }

    @Override
    protected byte[] loadDataFromDevice() {
        System.out.println("Loading data from device");
        simulateDeviceLatency();
        System.out.println("Loading complete\n");
        return new byte[0];
    }

    @Override
    protected boolean sendData(byte[] data) {
        System.out.println("Sending data to server");
        simulateNetworkLatency();
        System.out.println("Successfully sent data\n");
        return true;
    }

    @Override
    protected void showResult(boolean success, String info) {
        if (success) {
            System.out.println("Operation complete");
        } else {
            System.out.println("Fail, reason: " + info);
        }
    }

    @Override
    protected void logOut() {
        System.out.println("Logged out");
    }
}
