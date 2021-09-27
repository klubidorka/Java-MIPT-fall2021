package SOLID.ISP;

/**
 * Larger interfaces should be split into smaller ones.
 * So that we can ensure that implementing classes only need to be concerned
 * about the methods that are of interest to them. Moreover, clients can work with
 * classes using smaller interfaces, so that they don't know about methods that they don't need
 */
public interface TestingSystem {
    /**
     * Upload solution to testing system
     * @param solution code
     * @param taskId id of the problem
     * @return attempt ID
     */
    long uploadSolution(byte[] solution, long taskId);

    /**
     * Returns the result of solution test
     * @param attemptId ID of the attempt
     * @return result
     */
    byte[] getVerdict(long attemptId);

    /**
     * Download source code of solution
     * @param attemptId ID of the attempt
     * @return source code
     */
    byte[] downloadSolution(long attemptId);

    /**
     * Rerun tests for attempt
     * @param attemptId ID of the attempt
     */
    void reJudgeAttempt(long attemptId);
}

interface TestingSystemStudent {
    /**
     * Upload solution to testing system
     * @param solution code
     * @param taskId id of the problem
     * @return attempt ID
     */
    long uploadSolution(byte[] solution, long taskId);

    /**
     * Returns the result of solution test
     * @param attemptId ID of the attempt
     * @return result
     */
    byte[] getVerdict(long attemptId);
}

interface TestingSystemAdmin extends TestingSystemStudent {
    /**
     * Download source code of solution
     * @param attemptId ID of the attempt
     * @return source code
     */
    byte[] downloadSolution(long attemptId);

    /**
     * Rerun tests for attempt
     * @param attemptId ID of the attempt
     */
    void reJudgeAttempt(long attemptId);
}
