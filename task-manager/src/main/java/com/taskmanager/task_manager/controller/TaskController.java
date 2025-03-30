package com.taskmanager.task_manager.controller;

import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.service.TaskService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @Operation(summary = "Lista todas as tarefas", description = "Retorna uma lista com todas as tarefas cadastradas.")
    @ApiResponse(responseCode = "200", description = "Tarefas recuperadas com sucesso")
    @GetMapping
    public List<Task> getAllTasks() {
        return taskService.getAllTasks();
    }

    @Operation(summary = "Retorna uma tarefa específica", description = "Retorna a tarefa com o ID informado.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tarefa encontrada"),
            @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id) {
        Optional<Task> task = taskService.getTaskById(id);
        return task.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Atualiza uma tarefa existente", description = "Atualiza os dados de uma tarefa com base no ID.")
    @ApiResponse(responseCode = "200", description = "Tarefa atualizada com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Cria uma nova tarefa", description = "Cadastra uma nova tarefa no sistema.")
    @ApiResponse(responseCode = "201", description = "Tarefa criada com sucesso")
    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @Operation(summary = "Remove uma tarefa", description = "Remove uma tarefa do sistema pelo ID.")
    @ApiResponse(responseCode = "204", description = "Tarefa removida com sucesso")
    @ApiResponse(responseCode = "404", description = "Tarefa não encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        if (taskService.getTaskById(id).isPresent()) {
            taskService.deleteTask(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
