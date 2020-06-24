package maven.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * author: yang
 * datetime: 2020/5/26 11:17
 */

public class TestList {

    public static void test_list(){
        List<Map> param_data = new ArrayList(2);
        HashMap<String,Object> param2 = new HashMap<String,Object>();
        param2.put("a","1");
        param2.put("b","2");
        param_data.add(param2);
        HashMap param4 = (HashMap) param2.clone();
        Object param3 = ((ArrayList<Map>) param_data).clone();
//        param2.clear();
        param4.put("c","1");
        param4.put("d","2");
        param_data.add(param4);
        System.out.println(param_data);
    }


    public static void main(String[] args) {
        test_list();


//        Map<String,String> map = new HashMap<String,String>();
//
//
//        map.put("a", "1");
//
//        map.put("b", "2");
//
//        test(map);
//
//        System.out.println(map.get("a"));
//
//        System.out.println(map.get("b"));
//
//        System.out.println(map.get("c"));

    }

    public static void test(Map map){

        Map<String,String> map2 = new HashMap<String,String>();

        map=map2;

        map2.put("c", "3");
//        map.put("c","4");

    }

}
