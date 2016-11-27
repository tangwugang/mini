import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.config.IniSecurityManagerFactory;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.Factory;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Created by twg on 16/10/31.
 */
public class MyShiroTest {

    @Test
    public void testShiro(){

        Factory<org.apache.shiro.mgt.SecurityManager> securityManagerFactory = new IniSecurityManagerFactory("classpath:shiro-realm.ini");
        org.apache.shiro.mgt.SecurityManager securityManager = securityManagerFactory.getInstance();
        //绑定securityManager到SecurityUtils中
        SecurityUtils.setSecurityManager(securityManager);
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken("zhang","123");
        usernamePasswordToken.setRememberMe(true);
        //2.使用SecurityUtils获取subject的login
        try {
            subject.login(usernamePasswordToken);
        }catch (IncorrectCredentialsException e){
            throw new RuntimeException("用户名、密码错误");
        }

        Assert.assertEquals(true,subject.isAuthenticated());
    }
}
