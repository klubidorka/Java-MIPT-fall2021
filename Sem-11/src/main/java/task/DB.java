package task;

public interface DB {
    /**
     * Simulates reading process. Should be implemented in the way that many readers are able to read at the same time
     */
    void read(int readerID);

    /**
     * Simulates writing process. Should be implemented in the way that only one writer is able to write at the time.
     * If writer is writing there must be no readers reading at the moment
     */
    void write(int writerId);
}
