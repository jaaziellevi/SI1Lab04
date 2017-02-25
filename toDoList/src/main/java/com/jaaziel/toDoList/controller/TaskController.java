package com.jaaziel.toDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaaziel.toDoList.model.Task;
import com.jaaziel.toDoList.model.TaskList;
import com.jaaziel.toDoList.service.TaskService;

@RestController
public class TaskController {
	
	@Autowired
	private TaskService taskService;

	@RequestMapping("/tasks")
	public List<Task> getAllTasks(){
		return taskService.getAll();
	}
	
	@RequestMapping("/lists/{id}/tasks")
	public List<Task> getAllLists(@PathVariable Integer id){
		return taskService.getAllTasks(id);
	}
	
	@RequestMapping("lists/{listId}/tasks/{id}")
	public Task getTask(@PathVariable Integer id){
		return taskService.getTask(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "lists/{listId}/tasks")
	public void addTask(@RequestBody Task task, @PathVariable Integer listId){
		task.setTaskList(new TaskList(listId, ""));
		taskService.addTask(task);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "lists/{listId}/tasks/{id}")
	public void updateTask(@RequestBody Task task, @PathVariable Integer listId, @PathVariable Integer id){
		task.setTaskList(new TaskList(listId, ""));
		taskService.updateTask(task);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "lists/{listId}/tasks/{id}")
	public void deleteTask(@PathVariable Integer id){
		taskService.deleteTask(id);
	}
	
}
