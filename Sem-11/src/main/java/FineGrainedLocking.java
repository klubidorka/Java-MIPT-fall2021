import java.util.List;

public class FineGrainedLocking {
}


class DataTransferObject {
    private List<Object> firstList;
    private List<Object> secondList;

    public synchronized void setFirstList(List<Object> list) {
        firstList = list;
    }

    public synchronized void setSecondList(List<Object> list) {
        secondList = list;
    }
}

class DataTransferObjectImproved {
    private List<Object> firstList;
    private final Object firstListLock = new Object();

    private List<Object> secondList;
    private final Object secondListLock = new Object();

    public void setFirstList(List<Object> list) {
        synchronized (firstListLock) {
            firstList = list;
        }
    }

    public void setSecondList(List<Object> list) {
        synchronized (secondListLock) {
            secondList = list;
        }
    }
}