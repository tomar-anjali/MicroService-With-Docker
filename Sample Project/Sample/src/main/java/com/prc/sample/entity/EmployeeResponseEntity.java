package com.prc.sample.entity;

public class EmployeeResponseEntity {
	private Integer id;
	private String name;
    private String position;
    public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public EmployeeResponseEntity(Integer id, String name, String position) {
		super();
		this.id = id;
		this.name = name;
		this.position = position;
	}
	public EmployeeResponseEntity(Employee employee) {
		this.id=employee.getId();
		this.name=employee.getName();
		this.position=employee.getPosition();
	}
}
