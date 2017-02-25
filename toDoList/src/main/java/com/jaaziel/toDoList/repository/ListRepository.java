package com.jaaziel.toDoList.repository;

import org.springframework.data.repository.CrudRepository;

import com.jaaziel.toDoList.model.TaskList;

public interface ListRepository extends CrudRepository<TaskList, Integer> {
	
}
