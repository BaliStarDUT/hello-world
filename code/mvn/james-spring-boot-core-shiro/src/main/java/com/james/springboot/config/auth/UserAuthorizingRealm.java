package com.james.springboot.config.auth;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

/**
 * author: yang
 * datetime: 2021/9/22 20:28
 */
@Component
public class UserAuthorizingRealm extends AuthorizingRealm {

    private static Logger logger = LoggerFactory.getLogger(UserAuthorizingRealm.class);

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        User user = principalCollection.getPrimaryPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        Set<Permission> permissions = new HashSet<Permission>();
//        Permission permission = new Permission() {
//            @Override
//            public boolean implies(Permission permission) {
//                return true;
//            }
//        };
//        permissions.add(permission);
        info.setObjectPermissions(permissions);
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        String username = token.getUsername();
        char[] password = token.getPassword();
        SimpleAccount account = new SimpleAccount(username, password, this.getName(), null, (Set)null);

//        SimpleAccountRealm account = new SimpleAccountRealm();
//        account.addAccount(username, String.valueOf(password));
//        account.
        SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(account,password,"basic");
        return info;
    }
}
