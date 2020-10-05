package mybatis_spring_study.mapper;

import static org.junit.Assert.fail;

import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/context-root.xml"})
public class DepartmentMapperTest {

	protected static final Log log= LogFactory.getLog(DepartmentMapperTest.class);
	
	@Autowired
	private DepartmentMapper mapper;
	
	@After
	public void tearDown() throws Exception {
	   System.out.println();
	}
	
	@Test
	public void testSelectDepartmentAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testSelectDepartmentByNo() {
		fail("Not yet implemented");
	}

	@Test
	public void testInsertDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testUpdateDepartment() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteDepartment() {
		fail("Not yet implemented");
	}

}
