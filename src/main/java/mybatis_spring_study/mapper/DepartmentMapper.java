package mybatis_spring_study.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import mybatis_spring_study.dto.Department;

@Component
public interface DepartmentMapper {
	
	List<Department> selectDepartmentAll();
	Department selectDepartmentByNo(Department department);
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Department department);
	
}
