package template_method.report_sender;

import java.util.Random;

public class LatencySimulator {
    public static void simulateNetworkLatency() {
        Random random = new Random();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(".");
                Thread.sleep(50 + Math.abs(random.nextLong()) % 100);
            }
        } catch (InterruptedException ignored) {
        }
        System.out.println();
    }

    public static void simulateDeviceLatency() {
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print("\u2588");
                Thread.sleep(100);
            }
        } catch (InterruptedException ignored) {
        }
        System.out.println();
    }
}
