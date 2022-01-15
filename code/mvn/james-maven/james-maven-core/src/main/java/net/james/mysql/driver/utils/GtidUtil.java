package net.james.mysql.driver.utils;

import net.james.mysql.driver.packets.GTIDSet;
import net.james.mysql.driver.packets.MariaGTIDSet;
import net.james.mysql.driver.packets.MysqlGTIDSet;

/**
 * 类 GtidUtil.java 的实现
 *
 * @author winger 2020/9/24 1:25 下午
 * @version 1.0.0
 */
public class GtidUtil {

    public static GTIDSet parseGtidSet(String gtid, boolean isMariaDB) {
        if (isMariaDB) {
            return MariaGTIDSet.parse(gtid);
        }
        return MysqlGTIDSet.parse(gtid);
    }
}
