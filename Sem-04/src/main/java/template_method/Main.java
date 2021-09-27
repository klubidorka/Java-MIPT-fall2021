package template_method;

import template_method.report_sender.MobileReportSender;
import template_method.report_sender.ReportSender;

public class Main {
    public static void main(String[] args) {
        ReportSender sender = new MobileReportSender("klubidorka", "1234");
        sender.sendReport();
    }
}
