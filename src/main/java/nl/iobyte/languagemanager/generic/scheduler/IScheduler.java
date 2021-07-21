package nl.iobyte.languagemanager.generic.scheduler;

public interface IScheduler {

    /**
     * Run task Synchronous
     * @param r Runnable
     */
    void runSync(Runnable r);

    /**
     * Run task Asynchronous
     * @param r Runnable
     */
    void runAsync(Runnable r);

    /**
     * Run task Synchronous after x ticks
     * @param r Runnable
     * @param delay In ticks
     */
    void runSyncLater(Runnable r, long delay);

    /**
     * Run task Asynchronous after x ticks
     * @param r Runnable
     * @param delay In ticks
     */
    void runAsyncLater(Runnable r, long delay);

    /**
     * Run taskSynchronous after x ticks every y ticks
     * @param r Runnable
     * @param delay In ticks
     * @param period In ticks
     */
    int runSyncRepeating(Runnable r, long delay, long period);

    /**
     * Run task Asynchronous after x ticks every y ticks
     * @param r Runnable
     * @param delay In ticks
     * @param period In ticks
     */
    int runAsyncRepeating(Runnable r, long delay, long period);

    /**
     * Cancel task with id
     * @param taskId Integer
     */
    void cancelTask(int taskId);

}
