package com.employee.dto;

public class EmployeeDto {

	private Integer id;
	private String firstName;
	private String lastName;
	private String email;
	private String phno;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhno() {
		return phno;
	}

	public void setPhno(String phno) {
		this.phno = phno;
	}

	public EmployeeDto(Integer id, String firstName, String lastName, String email, String phno) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phno = phno;
	}

	public EmployeeDto(String firstName, String lastName, String email, String phno) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phno = phno;
	}

	@Override
	public String toString() {
		return "EmployeeDto [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", phno=" + phno + "]";
	}

	public EmployeeDto() {

	}

}
