package com.ln.junit;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class calculatortest {
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {	
	}
	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}
	@Before
	public void setUp() throws Exception {
	}
	@After
	public void tearDown() throws Exception {
	}
	@Test
	public void testadd() {
		calculator cal = new calculator();
		int result = cal.add(3, 5);
		assertEquals(8,result) ;
	}
	
	@Test
	public void testAssert(){
		String obj1 = "junit";
		String obj2 = "junit";
		String obj3 = "test";
		String obj4 = "test";
		String obj5 = null;
		
		int var1 = 1;
		int var2 = 2;
		
		//assertEquals(obj1, obj2);  
		//断言二个值是否相等
		
		//assertSame(obj3, obj4);    
		//断言二个对象引用是相同的
		
		//assertNotSame(obj3, obj4);    
		//断言二个对象引用是不相同的
		
		//assertNotNull(obj5);   
		//断言一个对象是不为空
		
		assertTrue(var1!= var2);   
		//断言一个条件为真
	}
	
	@Ignore
	@Test
	public void testsubstract() {
		calculator cal = new calculator();
		int result = cal.substract(3, 5);
		assertEquals(8,result) ;
	}
	@Ignore 
	@Test
	public void testmultiply() {
		calculator cal = new calculator();
		int result = cal.multiply(3, 5);
		assertEquals(8,result) ;
	}
	@Ignore 
	@Test
	public void testdivide() {
		calculator cal = new calculator();
		int result = cal.divide(3, 5);
		assertEquals(8,result) ;
	}
}
