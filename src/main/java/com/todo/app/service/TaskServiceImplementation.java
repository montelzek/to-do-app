package com.todo.app.service;

import com.todo.app.dao.TaskRepository;
import com.todo.app.entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService{

    private final TaskRepository taskRepository;

    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public List<Task> findAll() {
        return taskRepository.findAll();
    }

    @Override
    public Task findById(Integer theId) {
        Optional<Task> resultTask = taskRepository.findById(theId);

        Task theTask = null;
        if (resultTask.isPresent()) {
            theTask = resultTask.get();
        } else  {
            throw new RuntimeException("Did not find task with id: " + theId);
        }

        return theTask;
    }

    @Override
    public Task saveTask(Task theTask) {
        return taskRepository.save(theTask);
    }

    @Override
    public Task updateTask(Integer theId, Task updatedTask) {
        Task existingTask = taskRepository.findById(theId)
                .orElseThrow(() -> new RuntimeException("Task not found with id: " + theId));

        existingTask.setTitle(updatedTask.getTitle());
        existingTask.setDescription(updatedTask.getDescription());
        existingTask.setCompleted(updatedTask.getCompleted());

        return taskRepository.save(existingTask);
    }

    @Override
    public void deleteById(Integer theId) {
        taskRepository.deleteById(theId);
    }
}
