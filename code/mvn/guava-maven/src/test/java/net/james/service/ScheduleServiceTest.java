package net.james.service; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* ScheduleService Tester. 
* 
* @author <Authors name> 
* @since <pre>06/18/2020</pre> 
* @version 1.0 
*/ 
public class ScheduleServiceTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception {
    ScheduleService.main(new String[]{"a"});
}

/** 
* 
* Method: update_info_1_task() 
* 
*/ 
@Test
public void testUpdate_info_1_task() throws Exception {
    ScheduleService service = new ScheduleService();
    service.update_info("a","b","c","d");
}

/** 
* 
* Method: update_info_timer_task() 
* 
*/ 
@Test
public void testUpdate_info_timer_task() throws Exception {
    ScheduleService service = new ScheduleService();
    service.update_info_timer_task();
    service.update_info_1_task();
}

/** 
* 
* Method: update_info(String name, String namespace, String cluster, String Object) 
* 
*/ 
@Test
public void testUpdate_info() throws Exception { 
//TODO: Test goes here... 
} 


} 
