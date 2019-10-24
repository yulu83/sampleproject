package au.com.anz.sampleproject.sampleproject;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext
@ActiveProfiles("test")
public class SampleProjectApplicationTest {

	@Autowired
	private MockMvc mvc;

	@Test
	public void shouldReturnAccountForUser() throws Exception {
		mvc.perform(get("/api/v1/accounts/{uid}", "abc")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].userId").value("abc"))
				.andExpect(jsonPath("$[0].accountName").value("account name1"))
				.andExpect(jsonPath("$[1].accountName").value("account name2"));

		mvc.perform(get("/api/v1/accounts/{uid}", "def")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(1)))
				.andExpect(jsonPath("$[0].userId").value("def"))
				.andExpect(jsonPath("$[0].accountName").value("account name3"));

		mvc.perform(get("/api/v1/accounts/{uid}", "bbb")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void shouldReturnTransactionsForUserAccount() throws Exception {
		mvc.perform(get("/api/v1/transactions/{uid}/{accountNumber}", "abc", "account number1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(20)))
				.andExpect(jsonPath("$[0].userId").value("abc"))
				.andExpect(jsonPath("$[0].accountName").value("account name"))
				.andExpect(jsonPath("$[1].accountName").value("account name"))
				.andExpect(jsonPath("$[19].accountName").value("account name"));

		mvc.perform(get("/api/v1/transactions/{uid}/{accountNumber}", "def", "account number2")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(20)))
				.andExpect(jsonPath("$[0].userId").value("def"))
				.andExpect(jsonPath("$[0].accountName").value("account name"))
				.andExpect(jsonPath("$[1].accountName").value("account name"))
				.andExpect(jsonPath("$[19].accountName").value("account name"));

		mvc.perform(get("/api/v1/transactions/{uid}/{accountNumber}", "bbb", "account number1")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$", hasSize(0)));
	}
}
