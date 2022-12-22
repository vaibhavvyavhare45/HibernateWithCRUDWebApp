package com.warrior;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Employee {
	@Id
	@Column(name="empno")
	private int employeeId;
	@Column(name="ename")
	private String employeeName;
	@Column(name="sal")
	private int employeeSal;
	@Column(name="deptno")
	private int deptNumber;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public int getEmployeeSal() {
		return employeeSal;
	}

	public void setEmployeeSal(int employeeSal) {
		this.employeeSal = employeeSal;
	}

	public int getDeptNumber() {
		return deptNumber;
	}

	public void setDeptNumber(int deptNumber) {
		this.deptNumber = deptNumber;
	}

}
