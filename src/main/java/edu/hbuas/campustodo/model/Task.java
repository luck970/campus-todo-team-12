package edu.hbuas.campustodo.model;

import java.util.Objects;

/**
 * 校园任务。
 *
 * <p>任务由编号、标题和完成状态构成，编号由 {@code TaskService} 统一分配，
 * 标题不允许为空。</p>
 */
public class Task {
    // 1. 新增字段（默认优先级设为 MEDIUM）
    private edu.hbuas.campustodo.model.Priority priority = edu.hbuas.campustodo.model.Priority.MEDIUM;

    // 2. 新增 getter 方法
    public edu.hbuas.campustodo.model.Priority getPriority() {
        return priority;
    }

    // 3. 新增 setter 方法
    public void setPriority(edu.hbuas.campustodo.model.Priority priority) {
        this.priority = priority;
    }

    private final long id;
    private final String title;
    private boolean completed;

    /**
     * 创建一个未完成的任务。
     *
     * @param id    任务编号，由服务层分配
     * @param title 任务标题，不能为 null
     */
    public Task(long id, String title) {
        this.id = id;
        this.title = Objects.requireNonNull(title, "title 不能为 null");
        this.completed = false;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return String.format("#%d [%s] %s", id, completed ? "x" : " ", title);
    }
}
