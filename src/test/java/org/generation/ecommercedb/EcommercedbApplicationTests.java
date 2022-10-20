package org.generation.ecommercedb;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.generation.ecommercedb.model.Producto;
import org.generation.ecommercedb.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.containsString;
@SpringBootTest
@AutoConfigureMockMvc
class EcommercedbApplicationTests {
	@Autowired
	private MockMvc mockMvc;

	private User user;

	@Test
	public void testGet() throws Exception {
		this.mockMvc.perform(get("/api/productos/")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Pepsi")));
	}

	@Test
	public void testLogin(String username, String pass) throws Exception {
		user = new User(null,username,pass);
		this.mockMvc.perform(post("/api/login/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(user))).andDo(print()).andExpect(status().isOk());
	}

	@Test
	public void testMultiLogin() throws Exception {
		testLogin("daniel","12345");
		testLogin("daniel","1234");
	}

	@Test
	public void testPost() throws Exception{
		Producto p = new Producto();
		p.setURL_Imagen("test.png");
		p.setDescripcion("Test descripcion");
		p.setNombre("Test1");
		p.setPrecio(20.0);
		this.mockMvc.perform(post("/api/productos/").contentType(MediaType.APPLICATION_JSON)
				.content(asJsonString(p))).andExpect(status().isOk());
	}

	//Serializer
	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {throw new RuntimeException(e);
		}//catch}
	}
}
