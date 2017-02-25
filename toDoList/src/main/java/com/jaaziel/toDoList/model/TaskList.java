package com.jaaziel.toDoList.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class TaskList {

	@Id
	@GeneratedValue
	private Integer id;
	private String listName;
	
	public TaskList() {}

	public TaskList(Integer id, String listName) {
		super();
		this.id = id;
		this.listName = listName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getListName() {
		return listName;
	}

	public void setListName(String listName) {
		this.listName = listName;
	}
	
	
	
}
