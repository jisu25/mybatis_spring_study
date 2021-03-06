package mybatis_spring_study.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/context-root.xml"})
public class EmployeeServiceTest {

	protected static final Log log= LogFactory.getLog(EmployeeServiceTest.class);
	
	@Autowired
	private EmployeeService service;
	
	@After
	public void tearDown() throws Exception {
	   System.out.println();
	}
	
	@Test
	public void testAddEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Employee employee = new Employee(10, "김뫄뫄", "대리", new Employee(4377), 2600000, new Department(2));
		int res = service.addEmployee(employee);
		System.out.println(res);
		Assert.assertEquals(1, res);
	}

	@Test
	public void testDeleteEmployee() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		int res = service.deleteEmployee(new Employee(10));
		System.out.println(res);
		Assert.assertEquals(1, res);
	}

}
