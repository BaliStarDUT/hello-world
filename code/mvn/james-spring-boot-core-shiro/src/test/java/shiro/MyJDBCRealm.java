package shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyJDBCRealm extends JdbcRealm {
    Logger logger = LoggerFactory.getLogger(this.getClass().getName());

    @Override
    public String getName() {
        return "myrealm1";
    }


    @Override
    public boolean supports(AuthenticationToken authenticationToken) {
        //仅支持UsernamePasswordToken类型的Token
        return authenticationToken instanceof UsernamePasswordToken;
    }
    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
//        User user = (User) principals.getPrimaryPrincipal();
        String principal = (String) principals.getPrimaryPrincipal();
//        principals.asList();
        if(principal=="Admin"){
            info.addRole("admin");
        }
        return info;
    }
    /**
     * 登录认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationtoken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationtoken;
        String username = token.getUsername();
        String password = new String(token.getPassword());
        logger.info(username+" "+password);
        if(!"Ghost".equals(username)) {
            throw new UnknownAccountException(); //如果用户名错误
        }
        if(!"ghost".equals(password)) {
            throw new IncorrectCredentialsException(); //如果密码错误
        }

        SimpleAuthenticationInfo authinfo = new SimpleAuthenticationInfo(username,
                password, getName());
        return authinfo;
    }

}
