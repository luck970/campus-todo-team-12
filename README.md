# CampusTodo

CampusTodo 是一个控制台版校园任务管理器，作为《软件工程》综合实践
「Git 与 GitHub 团队协同开发」实验的载体。

当前版本：支持新增任务和列出任务。

## 功能

| 功能 | 入口 |
| --- | --- |
| 新增任务 | `TaskService#addTask(String title)` |
| 列出全部任务 | `TaskService#listAll()` |

## 任务模型

`Task` 包含 `id`、`title`、`completed` 三个字段；`id` 由 `TaskService` 自增分配，
`title` 不允许为空。

## 构建与测试

```bash
mvn -B verify
```

## 目录结构

```text
campus-todo/
├─ pom.xml
├─ README.md
├─ .gitignore
├─ src/main/java/edu/hbuas/campustodo/
│  ├─ model/Task.java
│  └─ service/TaskService.java
└─ src/test/java/edu/hbuas/campustodo/service/
   └─ TaskServiceTest.java
```

## 环境要求

- JDK 17
- Maven 3.8 或更高版本
- IntelliJ IDEA

## 使用说明

1. 克隆仓库：`git clone <REPOSITORY_URL>`
2. 在 IntelliJ 中以 Maven 项目导入，等待依赖同步完成。
3. 运行测试：`mvn -B verify`
