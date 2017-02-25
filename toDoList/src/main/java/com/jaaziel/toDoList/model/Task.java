package com.jaaziel.toDoList.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Task {

	@Id
	@GeneratedValue
	private Integer id;
	private String taskTitle;
	private String taskDescription;
	private Boolean taskDone;
	private Category category;
	private Priority priority;
	
	@ManyToOne
	private TaskList taskList;

	public Task() {
		this.taskDescription = "";
		this.taskDone = false;
		this.category = Category.NONE;
		this.priority = Priority.LOW;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTaskTitle() {
		return taskTitle;
	}

	public void setTaskTitle(String taskTitle) {
		this.taskTitle = taskTitle;
	}

	public String getTaskDescription() {
		return taskDescription;
	}

	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}

	public Boolean getTaskDone() {
		return taskDone;
	}

	public void setTaskDone(Boolean taskDone) {
		this.taskDone = taskDone;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	public TaskList getTaskList() {
		return taskList;
	}

	public void setTaskList(TaskList taskList) {
		this.taskList = taskList;
	}
	
}
