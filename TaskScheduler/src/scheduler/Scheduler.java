package scheduler;

import dto.Task;

public interface Scheduler {
    void scheduleTask(Task task);
    void showTasks();
}
