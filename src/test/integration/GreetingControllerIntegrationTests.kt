import org.adex.kotlingdemo.KotlingdemoApplication
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.*


@SpringBootTest(classes = [KotlingdemoApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
class GreetingControllerIntegrationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Test
    fun sayHello_ShouldReturnHelloWithName() {
        val name = "med";

        val result = webTestClient.get()
            .uri("/greeting/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals("Hello, ${name.uppercase(Locale.getDefault())}", result.responseBody)
    }
}