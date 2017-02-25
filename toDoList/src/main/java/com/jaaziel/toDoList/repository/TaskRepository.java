package com.jaaziel.toDoList.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.jaaziel.toDoList.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer> {
	public List<Task> findByTaskListId(Integer listId);
}
