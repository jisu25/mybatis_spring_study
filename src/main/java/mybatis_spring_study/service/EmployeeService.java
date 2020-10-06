package mybatis_spring_study.service;

import mybatis_spring_study.dto.Employee;

public interface EmployeeService {
	
	public int addEmployee(Employee employee);
	public int deleteEmployee(Employee employee);
	
}
