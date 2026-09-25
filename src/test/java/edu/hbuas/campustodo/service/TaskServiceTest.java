package edu.hbuas.campustodo.service;
import edu.hbuas.campustodo.model.Priority;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.hbuas.campustodo.model.Task;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * 基线测试：覆盖新增任务与空标题校验。
 */
class TaskServiceTest {

    private TaskService service;

    @BeforeEach
    void setUp() {
        service = new TaskService();
    }

    @Test
    @DisplayName("新增任务后可以从列表中查询到，且编号自增")
    void shouldAddTaskAndAssignIncrementingId() {
        Task first = service.addTask("复习软件工程");
        Task second = service.addTask("完成 Git 实验");

        assertAll(
                () -> assertEquals(1L, first.getId()),
                () -> assertEquals(2L, second.getId()),
                () -> assertEquals("复习软件工程", first.getTitle()),
                () -> assertFalse(first.isCompleted()));
    }

    @Test
    @DisplayName("新增的任务默认未完成，并按新增顺序列出")
    void shouldListAllTasksInInsertionOrder() {
        service.addTask("复习软件工程");
        service.addTask("完成 Git 实验");

        List<Task> all = service.listAll();

        assertAll(
                () -> assertEquals(2, all.size()),
                () -> assertEquals("复习软件工程", all.get(0).getTitle()),
                () -> assertEquals("完成 Git 实验", all.get(1).getTitle()),
                () -> assertTrue(all.stream().noneMatch(Task::isCompleted)));
    }

    @Test
    @DisplayName("没有任务时列出空列表")
    void shouldReturnEmptyListWhenNoTask() {
        assertTrue(service.listAll().isEmpty());
    }

    @Test
    @DisplayName("标题为空白时拒绝新增任务")
    void shouldRejectBlankTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask("   "));
        assertTrue(service.listAll().isEmpty(), "校验失败时不应留下半个任务");
    }

    @Test
    @DisplayName("标题为 null 时拒绝新增任务")
    void shouldRejectNullTitle() {
        assertThrows(IllegalArgumentException.class, () -> service.addTask(null));
    }
    @Test
    @DisplayName("根据优先级筛选任务，只返回匹配优先级的任务")
    void testFilterByPriority() {
        // Given: 准备不同优先级的任务
        Task highTask1 = service.addTask("写高优先级作业");
        highTask1.setPriority(Priority.HIGH); // 这里会红，因为Task还没这方法

        Task highTask2 = service.addTask("复习高优先级考试");
        highTask2.setPriority(Priority.HIGH);

        Task lowTask = service.addTask("看剧");
        lowTask.setPriority(Priority.LOW);

        // When: 调用即将实现的方法
        List<Task> highPriorityTasks = service.filterByPriority(Priority.HIGH); // 这里也会红

        // Then: 断言结果
        assertAll(
                () -> assertEquals(2, highPriorityTasks.size(), "应该只筛出 2 个高优先级任务"),
                () -> assertTrue(highPriorityTasks.stream().allMatch(t -> t.getPriority() == Priority.HIGH), "筛选出的任务必须都是 HIGH 优先级")
        );
    }
}
