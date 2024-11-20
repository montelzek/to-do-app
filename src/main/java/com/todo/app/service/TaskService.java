package com.todo.app.service;

import com.todo.app.entity.Task;

import java.util.List;

public interface TaskService {

    List<Task> findAll();

    Task findById(Integer theId);

    Task saveTask(Task theTask);

    Task updateTask(Integer theId, Task updatedTask);

    void deleteById(Integer theId);
}
