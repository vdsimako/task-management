package ru.vdsimako.taskmanagement.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import ru.vdsimako.taskmanagement.model.dto.TaskDto;
import ru.vdsimako.taskmanagement.model.dto.TaskManagementExceptionDto;
import ru.vdsimako.taskmanagement.model.dto.UpdateTaskDto;
import ru.vdsimako.taskmanagement.model.entity.Task;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class TaskControllerMvcTest {

    private static final String TASK_CONTROLLER_BASE_URI = "/task";

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private Map<Long, Task> taskMap;
    @Autowired
    private AtomicLong taskSequence;

    @BeforeEach
    public void beforeEach() {
        taskSequence.set(0L);
        taskMap.clear();
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#getTaskById_whenId_thenTaskDto")
    void getTaskById_whenId_thenTaskDto(Long taskId,
                                        Task taskExpected,
                                        TaskDto taskDtoExpected) throws Exception {
        taskMap.put(taskDtoExpected.getId(), taskExpected);

        MvcResult mvcResult = mockMvc.perform(get("/task/{taskId}", taskId))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskDto taskDtoActual = objectMapper.readValue(contentAsString, TaskDto.class);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#getTaskById_whenId_thenException")
    void getTaskById_whenId_thenException(Long taskId,
                                          TaskManagementExceptionDto taskManagementExceptionDtoExpected) throws Exception {
        taskMap.remove(taskId);

        MvcResult mvcResult = mockMvc.perform(get("/task/{taskId}", taskId))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskManagementExceptionDto taskManagementExceptionDtoActual = objectMapper.readValue(contentAsString, TaskManagementExceptionDto.class);

        assertEquals(taskManagementExceptionDtoExpected, taskManagementExceptionDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#getTaskList_thenTaskDtoList")
    void getTaskList_thenTaskDtoList(List<Task> taskList,
                                     List<TaskDto> taskDtoListExpected) throws Exception {
        taskList.forEach(task -> taskMap.put(task.getId(), task));

        MvcResult mvcResult = mockMvc.perform(get("/task"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        List<TaskDto> taskDtoListActual = objectMapper.readValue(contentAsString, new TypeReference<List<TaskDto>>() {

        });

        assertThat(taskDtoListActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoListExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#createTask_whenTaskDto_thenTaskDto")
    void createTask_whenTaskDto_thenTaskDto(TaskDto taskDto,
                                            TaskDto taskDtoExpected) throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskDto taskDtoActual = objectMapper.readValue(contentAsString, TaskDto.class);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#createTask_whenTaskDto_thenBadRequest")
    void createTask_whenTaskDto_thenBadRequest(TaskDto taskDto) throws Exception {
        mockMvc.perform(
                post("/task")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(taskDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#updateTask_whenUpdateTaskDto_thenTaskDto")
    void updateTask_whenUpdateTaskDto_thenTaskDto(Long taskId,
                                                  Task taskExpected,
                                                  UpdateTaskDto updateTaskDto,
                                                  TaskDto taskDtoExpected) throws Exception {
        taskMap.put(taskId, taskExpected);

        MvcResult mvcResult = mockMvc.perform(
                put("/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTaskDto)))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskDto taskDtoActual = objectMapper.readValue(contentAsString, TaskDto.class);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#updateTask_whenUpdateTaskDto_thenException")
    void updateTask_whenUpdateTaskDto_thenException(Long taskId,
                                                    UpdateTaskDto updateTaskDto,
                                                    TaskManagementExceptionDto taskManagementExceptionDtoExpected) throws Exception {
        taskMap.remove(taskId);

        MvcResult mvcResult = mockMvc.perform(
                put("/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTaskDto)))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskManagementExceptionDto taskManagementExceptionDtoActual = objectMapper.readValue(contentAsString, TaskManagementExceptionDto.class);

        assertEquals(taskManagementExceptionDtoExpected, taskManagementExceptionDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#updateTask_whenUpdateTaskDto_thenBadRequest")
    void updateTask_whenUpdateTaskDto_thenBadRequest(Long taskId,
                                                     UpdateTaskDto updateTaskDto) throws Exception {
        mockMvc.perform(
                put("/task/{taskId}", taskId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(updateTaskDto)))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#deleteTaskById_whenTaskId_thenTaskDto")
    void deleteTaskById_whenTaskId_thenTaskDto(Long taskId,
                                               Task task,
                                               TaskDto taskDtoExpected) throws Exception {
        taskMap.put(taskId, task);

        MvcResult mvcResult = mockMvc.perform(delete("/task/{taskId}", taskId))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskDto taskDtoActual = objectMapper.readValue(contentAsString, TaskDto.class);

        assertThat(taskDtoActual)
                .usingRecursiveComparison()
                .ignoringFields("lastModifiedDate")
                .isEqualTo(taskDtoExpected);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#deleteTaskById_whenTaskId_thenException")
    void deleteTaskById_whenTaskId_thenException(Long taskId,
                                                 TaskManagementExceptionDto taskManagementExceptionDtoExpected) throws Exception {
        taskMap.remove(taskId);

        MvcResult mvcResult = mockMvc.perform(delete("/task/{taskId}", taskId))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andReturn();

        String contentAsString = mvcResult.getResponse().getContentAsString();

        TaskManagementExceptionDto taskManagementExceptionDtoActual = objectMapper.readValue(contentAsString, TaskManagementExceptionDto.class);

        assertEquals(taskManagementExceptionDtoExpected, taskManagementExceptionDtoActual);
    }

    @ParameterizedTest
    @MethodSource("ru.vdsimako.taskmanagement.argument.TaskControllerMvcTestArg#deleteTaskById_whenTaskId_thenBadRequest")
    void deleteTaskById_whenTaskId_thenBadRequest(Long taskId) throws Exception {
        mockMvc.perform(delete("/task/{taskId}", taskId))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andReturn();
    }
}