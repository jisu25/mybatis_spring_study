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
public class TransactionAOPServiceTest {

	protected static final Log log= LogFactory.getLog(TransactionAOPServiceTest.class);
	
	@Autowired
	private TransactionAOPService service;
	
	@After
	public void tearDown() throws Exception {
	   System.out.println();
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testATrRegister_Dept_fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(1, "인사", 10); // 겹침 -> rollback
		Employee employee = new Employee(1006, "박규영", "과장", new Employee(4377), 410000, department); // 성공이지만 위에서 롤백
	
		service.trRegister(department, employee);
	}
	
	@Test(expected = DuplicateKeyException.class)
	public void testBTrRegister_Emp_fail() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7, "인사", 10); // 성공
		Employee employee = new Employee(4377, "박규영", "과장", new Employee(4377), 410000, department); // 겹침 -> rollback
	
		service.trRegister(department, employee);
	}
	
	@Test
	public void testCTrRegister_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7, "인사", 10);
		Employee employee = new Employee(1006, "박규영", "과장", new Employee(4377), 410000, department);
		
		service.trRegister(department, employee);
	}

	
	
	@Test(expected=RuntimeException.class)
	public void testDUnRegisterTransaction_Fail_Department() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(100); // RuntimeException -> rollback
		Employee employee = new Employee(1006); // rollback되므로 삭제되면 안 됨
		
		service.trUnRegister(department, employee);
	}
	
	
	@Test(expected = RuntimeException.class)
	public void testEUnRegisterTransaction_Fail_Emp() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7); // 성공
		Employee employee = new Employee(9999); // rollback
		
		service.trUnRegister(department, employee);
	}
	
	@Test
	public void testFUnRegisterTransaction_Success() {
		log.debug(Thread.currentThread().getStackTrace()[1].getMethodName() + "()");
		Department department = new Department(7); // 성공
		Employee employee = new Employee(1006); // 성공
		
		service.trUnRegister(department, employee); // 커밋
	}

}
