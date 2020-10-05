package mybatis_spring_study.mapper;

import java.util.List;

import mybatis_spring_study.dto.Department;

public interface DepartmentMapper {
	
	List<Department> selectDepartmentAll();
	Department selectDepartmentByNo(Department department);
	int insertDepartment(Department department);
	int updateDepartment(Department department);
	int deleteDepartment(Department department);
	
}
