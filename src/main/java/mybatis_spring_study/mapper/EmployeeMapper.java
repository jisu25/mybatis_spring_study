package mybatis_spring_study.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import mybatis_spring_study.dto.Employee;

@Component
public interface EmployeeMapper {
	
//	List<Employee> selectEmployeeAll();
//	Employee selectEmployeeByNo(Employee employee);
	int insertEmployee(Employee employee);
//	int updateEmployee(Employee employee);
	int deleteEmployee(Employee employee);
	
}
