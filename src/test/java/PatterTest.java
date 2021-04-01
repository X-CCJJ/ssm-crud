import com.ssm.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class PatterTest {
    @Autowired
    EmployeeService employeeService;

    @Test
    public void empNameTest(){
        boolean testEmpName = employeeService.testEmpName("az");
        System.out.println(testEmpName);
    }

    @Test
    public void emailTest(){
        boolean testEmail = employeeService.testEmail("aa@123.com");
        System.out.println(testEmail);
    }
}
