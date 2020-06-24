package net.james; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After; 

/** 
* App Tester. 
* 
* @author <Authors name> 
* @since <pre>06/18/2020</pre> 
* @version 1.0 
*/ 
public class AppTest { 

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
    App app = new App();
    app.main(new String[]{"a"});
    App.main(new String[]{"a"});
} 


} 
