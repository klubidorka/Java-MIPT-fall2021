package annotations;

@ControlledObject(name = "biscuits")
public class Cookies {

    @StartObject
    public void createCookie() {
        // Бизнес-логика
    }

    @StopObject
    public void stopCookie() {
        // Бизнес-логика
    }
}