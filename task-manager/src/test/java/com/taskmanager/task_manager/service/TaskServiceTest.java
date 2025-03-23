package com.taskmanager.task_manager.service;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import com.taskmanager.task_manager.model.Task;
import com.taskmanager.task_manager.repository.TaskRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskService taskService;

    private Task task;

    @BeforeEach
    void setUp() {
        task = new Task();
        task.setId(1L);
        task.setTitle("Nova Tarefa");
        task.setDescription("Descrição da tarefa");
    }

    @Test
    void deveRetornarTodasAsTarefas() {
        List<Task> mockTasks = Collections.singletonList(task);
        when(taskRepository.findAll()).thenReturn(mockTasks);

        List<Task> result = taskService.getAllTasks();

        assertEquals(1, result.size());
        assertEquals("Nova Tarefa", result.get(0).getTitle());
        verify(taskRepository, times(1)).findAll();
    }

    @Test
    void deveRetornarTarefaPorIdSeExistir() {
        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));

        Optional<Task> result = taskService.getTaskById(1L);

        assertTrue(result.isPresent());
        assertEquals("Nova Tarefa", result.get().getTitle());
        verify(taskRepository, times(1)).findById(1L);
    }

    @Test
    void deveRetornarVazioQuandoTarefaNaoExistir() {
        when(taskRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Task> result = taskService.getTaskById(2L);

        assertFalse(result.isPresent());
        verify(taskRepository, times(1)).findById(2L);
    }

    @Test
    void deveCriarUmaNovaTarefa() {
        when(taskRepository.save(task)).thenReturn(task);

        Task result = taskService.createTask(task);

        assertNotNull(result);
        assertEquals("Nova Tarefa", result.getTitle());
        verify(taskRepository, times(1)).save(task);
    }

    @Test
    void deveAtualizarUmaTarefaExistente() {
        Task updatedTask = new Task();
        updatedTask.setId(1L);
        updatedTask.setTitle("Tarefa Atualizada");

        when(taskRepository.findById(1L)).thenReturn(Optional.of(task));
        when(taskRepository.save(any(Task.class))).thenReturn(updatedTask);

        Optional<Task> result = taskService.updateTask(1L, updatedTask);

        assertTrue(result.isPresent());
        assertEquals("Tarefa Atualizada", result.get().getTitle());
        verify(taskRepository, times(1)).save(any());
    }

    @Test
    void deveDeletarUmaTarefaPorId() {

        doNothing().when(taskRepository).deleteById(1L);

        taskService.deleteTask(1L);

        verify(taskRepository, times(1)).deleteById(1L);
    }

}