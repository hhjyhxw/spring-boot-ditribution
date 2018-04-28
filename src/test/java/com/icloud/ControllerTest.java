///**
// * @author
// * @version
// * 2018年3月27日 上午9:57:07
// */
//package com.icloud;
//
//import static org.hamcrest.Matchers.equalTo;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import com.icloud.web.business.front.goods.BeanGoodFrontController;
//
///**
// * 类名称: ControllerTest
// * 类描述: 
// * 创建人: zhangdehai
// * 创建时间:2018年3月27日 上午9:57:07
// */
//
//@SpringBootTest
//public class ControllerTest {
//	  private MockMvc mockMvc;
//
//	  @Before
//	  public void setUp() throws Exception {
//	    mockMvc = MockMvcBuilders.standaloneSetup(new BeanGoodFrontController()).build();
//	  }
//
//	  @Test
//	  public void getHello() throws Exception {
//	    mockMvc.perform(MockMvcRequestBuilders.get("/hello1").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//	    .andExpect(content().string(equalTo("Hello World")));
//	  }
//
//	  @Test
//	  public void getHello2() throws Exception {
//	    mockMvc.perform(MockMvcRequestBuilders.get("/hello2").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
//	    .andExpect(content().string(equalTo("[\"A\",\"B\",\"C\"]")));
//	  }
//}
