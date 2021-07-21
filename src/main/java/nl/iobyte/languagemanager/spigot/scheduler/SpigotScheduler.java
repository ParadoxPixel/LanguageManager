package nl.iobyte.languagemanager.spigot.scheduler;

import nl.iobyte.languagemanager.generic.scheduler.IScheduler;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;

public class SpigotScheduler implements IScheduler {

    private final Plugin plugin;

    public SpigotScheduler(Plugin plugin) {
        this.plugin = plugin;
    }

    /**
     * Run task Synchronous
     * @param r Runnable
     */
    public void runSync(Runnable r) {
        if(Bukkit.isPrimaryThread() || !plugin.isEnabled()) {
            r.run();
            return;
        }

        Bukkit.getScheduler().runTask(plugin, r);
    }

    /**
     * Run task Asynchronous
     * @param r Runnable
     */
    public void runAsync(Runnable r) {
        if(!plugin.isEnabled()) {
            r.run();
            return;
        }

        Bukkit.getScheduler().runTaskAsynchronously(plugin, r);
    }

    /**
     * Run task Synchronous after x ticks
     * @param r Runnable
     * @param delay In ticks
     */
    public void runSyncLater(Runnable r, long delay) {
        if(!plugin.isEnabled()) {
            r.run();
            return;
        }

        Bukkit.getScheduler().runTaskLater(plugin, r, delay);
    }

    /**
     * Run task Asynchronous after x ticks
     * @param r Runnable
     * @param delay In ticks
     */
    public void runAsyncLater(Runnable r, long delay) {
        if(!plugin.isEnabled()) {
            r.run();
            return;
        }

        Bukkit.getScheduler().runTaskLaterAsynchronously(plugin, r, delay);
    }

    /**
     * Run taskSynchronous after x ticks every y ticks
     * @param r Runnable
     * @param delay In ticks
     * @param period In ticks
     */
    public int runSyncRepeating(Runnable r, long delay, long period) {
        if(!plugin.isEnabled())
            return -1;

        return Bukkit.getScheduler().runTaskTimer(plugin, r, delay, period).getTaskId();
    }

    /**
     * Run task Asynchronous after x ticks every y ticks
     * @param r Runnable
     * @param delay In ticks
     * @param period In ticks
     */
    public int runAsyncRepeating(Runnable r, long delay, long period) {
        if(!plugin.isEnabled())
            return -1;

        return Bukkit.getScheduler().runTaskTimerAsynchronously(plugin, r, delay, period).getTaskId();
    }

    /**
     * Cancel task with id
     * @param taskId Integer
     */
    public void cancelTask(int taskId) {
        Bukkit.getScheduler().cancelTask(taskId);
    }

}
