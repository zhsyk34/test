import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

//测试定时器
public class TaskTest {

    public static void main(String[] args) {
        ApplicationContext springContext = new ClassPathXmlApplicationContext(new String[]{"classpath:spring3.xml"});
    }
}
