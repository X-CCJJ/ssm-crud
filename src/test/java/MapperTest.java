import com.ssm.bean.Department;
import com.ssm.bean.Employee;
import com.ssm.dao.DepartmentMapper;
import com.ssm.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

// 拿到spring容器进行spring测试
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    SqlSession sqlSession;

    @Test
    public void testInsert(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 100; i++) {
            String uuid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(new Employee(null, uuid, "F", uuid+"@123.com", 2));
        }
        System.out.println("批量插入完成");
    }

    @Test
    public void testSelect(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectByPrimaryKeyWithDept(1);
        System.out.println(employee);
    }

    @Test
    public void testSelectEmp(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        Employee employee = mapper.selectByEmpName("xie");
        System.out.println(employee);
    }

    @Test
    public void testDeleteBatch(){
        EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
        List<Integer> list  = new ArrayList<>();
        list.add(204);
        list.add(205);
        list.add(206);
        mapper.deleteBatch(list);
    }

    @Test
    public void testSelectDept(){
        DepartmentMapper mapper = sqlSession.getMapper(DepartmentMapper.class);
        List<Department> departments = mapper.selectDept();
        System.out.println(departments);
    }
}
