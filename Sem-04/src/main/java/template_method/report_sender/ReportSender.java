package template_method.report_sender;

public abstract class ReportSender {
    private final String login;
    private final String password;

    public ReportSender(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public void sendReport() {
        if (logIn(login, password)) {
            byte[] data = loadDataFromDevice();
            boolean result = sendData(data);
            if (result) {
                showResult(true, "OK");
            } else {
                showResult(false, "Failed to send data");
            }
            logOut();
        } else {
            showResult(false, "Failed to log in");
        }
    }

    protected abstract boolean logIn(String login, String password);
    protected abstract byte[] loadDataFromDevice();
    protected abstract boolean sendData(byte[] data);
    protected abstract void showResult(boolean success, String info);
    protected abstract void logOut();
}
