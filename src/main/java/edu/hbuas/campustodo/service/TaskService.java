package edu.hbuas.campustodo.service;

import edu.hbuas.campustodo.model.Task;
import java.util.ArrayList;
import java.util.List;

/**
 * 任务服务：管理任务的新增与查询。
 *
 * <p>本类不依赖数据库，全部任务保存在内存中，便于实验中的单元测试。</p>
 */
public class TaskService {

    private final List<Task> tasks = new ArrayList<>();
    private long nextId = 1L;

    /**
     * 新增一个任务。
     *
     * @param title 任务标题，不能为 null 或空白
     * @return 新增的任务
     * @throws IllegalArgumentException 标题为 null 或空白时抛出
     */
    public Task addTask(String title) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("任务标题不能为空");
        }
        Task task = new Task(nextId++, title.trim());
        tasks.add(task);
        return task;
    }

    /**
     * 列出全部任务。
     *
     * @return 按新增顺序排列的任务列表；没有任务时返回空列表
     */
    public List<Task> listAll() {
        return List.copyOf(tasks);
    }
}
