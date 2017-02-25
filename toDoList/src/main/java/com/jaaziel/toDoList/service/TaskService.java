package com.jaaziel.toDoList.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaaziel.toDoList.model.Task;
import com.jaaziel.toDoList.repository.TaskRepository;

@Service
public class TaskService {
	
	@Autowired
	private TaskRepository taskRepository;

	public List<Task> getAll(){
		List<Task> tasks = new ArrayList<>();
		taskRepository.findAll().forEach(tasks::add);
		return tasks;
	}
	
	public List<Task> getAllTasks(Integer listId){
		List<Task> tasks = new ArrayList<>();
		taskRepository.findByTaskListId(listId).forEach(tasks::add);
		return tasks;
	}
	
	public Task getTask(Integer id){
		return taskRepository.findOne(id);
	}

	public void addTask(Task task) {
		taskRepository.save(task);
	}

	public void updateTask(Task task) {
		taskRepository.save(task);
	}

	public void deleteTask(Integer id) {
		taskRepository.delete(id);
	}

}
