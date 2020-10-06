package mybatis_spring_study.service;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import mybatis_spring_study.dto.Department;
import mybatis_spring_study.dto.Employee;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/context-root.xml"})
public class TransactionServiceTest {

	protected static final Log log= LogFactory.getLog(EmployeeServiceTest.class);
	
	@Autowired
	private TransactionService service;
	
	@After
	public void tearDown() throws Exception {
	   System.out.println();
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testARegisterTransaction_Dept_fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(1, "테스크포스", 10); // 겹침 -> rollback
		Employee employee = new Employee(1004, "박신혜", "과장", new Employee(4377), 410000, department); // 성공이지만 위에서 롤백
	
		service.registerTransaction(department, employee);
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testBRegisterTransaction_Emp_fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "테스크포스", 10); // 성공
		Employee employee = new Employee(4377, "박신혜", "과장", new Employee(4377), 410000, department); // 겹침 -> rollback
	
		service.registerTransaction(department, employee);
	}
	
	@Test
	public void testCRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6, "테스크포스", 10);
		Employee employee = new Employee(1005, "박신혜", "과장", new Employee(4377), 410000, department);
		
		service.registerTransaction(department, employee);
	}

	
	
	@Test(expected=RuntimeException.class)
	public void testDUnRegisterTransaction_Fail_Department() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(100); // RuntimeException -> rollback
		Employee employee = new Employee(1005); // rollback되므로 삭제되면 안 됨
		
		service.unRegisterTransaction(department, employee);
	}
	
	
	@Test
	public void testEUnRegisterTransaction_Fail_Emp() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6); // 성공
		Employee employee = new Employee(9999); // rollback
		
		service.unRegisterTransaction(department, employee);
	}
	
	@Test
	public void testFUnRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(6); // 성공
		Employee employee = new Employee(1005); // 성공
		
		service.unRegisterTransaction(department, employee); // 커밋
	}

}
