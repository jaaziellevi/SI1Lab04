package com.jaaziel.toDoList.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.jaaziel.toDoList.model.TaskList;
import com.jaaziel.toDoList.service.ListService;

@RestController
public class TaskListController {
	
	@Autowired
	private ListService listService;
	
	@RequestMapping("/lists")
	public List<TaskList> getAllLists(){
		return listService.getAllLists();
	}
	
	@RequestMapping("lists/{id}")
	public TaskList getList(@PathVariable Integer id){
		return listService.getList(id);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "list")
	public void addList(@RequestBody TaskList list){
		listService.addList(list);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "list/{id}")
	public void updateList(@RequestBody TaskList list){
		listService.updateList(list);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "list/{id}")
	public void deleteList(@PathVariable Integer id){
		listService.deleteList(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "lists")
	public void deleteAllList(){
		listService.deleteAllLists();
	}
}
