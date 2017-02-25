package com.jaaziel.toDoList.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jaaziel.toDoList.model.TaskList;
import com.jaaziel.toDoList.repository.ListRepository;

@Service
public class ListService {

	@Autowired
	private ListRepository listRepository;
	
	public List<TaskList> getAllLists(){
		List<TaskList> lists = new ArrayList<>();
		listRepository.findAll().forEach(lists::add);
		return lists;
	}
	
	public TaskList getList(Integer id){
		return listRepository.findOne(id);
	}
	
	public void addList(TaskList list){
		listRepository.save(list);
	}
	
	public void updateList(TaskList list){
		listRepository.save(list);
	}
	
	public void deleteList(Integer id){
		listRepository.delete(id);
	}

	public void deleteAllLists() {
		listRepository.deleteAll();
	}
	
}
