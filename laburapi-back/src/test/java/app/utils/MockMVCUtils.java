package app.utils;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class MockMVCUtils {

	@Autowired
	private ObjectMapper objectMapper;

	// Testea con MockMvc respuestas que no requieren carga útil en la petición
	public String controllerResponseTesterNoPayloadUtil
	(MockMvc mockMvc, MockHttpServletRequestBuilder requestBuilder, HttpStatus httpStatus) throws Exception {

		String response = mockMvc
				// When
				.perform(requestBuilder)
				// Then
				.andExpect(status().is(httpStatus.value()))
				.andReturn().getResponse().getContentAsString();
		return response;
	}

	// Testea con MockMvc respuestas que requieren carga útil en la petición (Válido para objeto y para String con formato Json)
	public <T> String controllerResponseTesterPayloadUtil
	(MockMvc mockMvc, T payLoad, MockHttpServletRequestBuilder requestBuilder, HttpStatus httpStatus) throws Exception {

		String response;
		
		if (!payLoad.getClass().equals(String.class)) {

			response = mockMvc
					// When
					.perform(requestBuilder
							.content(objectMapper.writeValueAsString(payLoad))
							.contentType(MediaType.APPLICATION_JSON))
					// Then
					.andExpect(status().is(httpStatus.value()))
					.andReturn().getResponse().getContentAsString();
			return response;
			
		} else {
			
			response = mockMvc
					// When
					.perform(requestBuilder
							.content((String) payLoad)
							.contentType(MediaType.APPLICATION_JSON))
					// Then
					.andExpect(status().is(httpStatus.value()))
					.andReturn().getResponse().getContentAsString();
			return response;
		}
	}
}
