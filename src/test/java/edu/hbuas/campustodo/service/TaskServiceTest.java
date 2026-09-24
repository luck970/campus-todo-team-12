package edu.hbuas.campustodo.service;

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
}
