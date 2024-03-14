import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import org.adex.kotlingdemo.controllers.GreetingController
import org.adex.kotlingdemo.services.GreetingServices
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import java.util.*


@ContextConfiguration(classes = [TestConfig::class])
@WebMvcTest(controllers = [GreetingController::class])
@AutoConfigureWebClient
class GreetingControllerUnitTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @MockkBean
    lateinit var greetingServicesMock: GreetingServices

    @Test
    fun sayHello_ShouldReturnHelloWithName() {
        val name = "med";

        every {
            greetingServicesMock.getGreeting(any())
        } returns "Hello, ${name.uppercase(Locale.getDefault())}"

        val result = webTestClient.get()
            .uri("/greeting/{name}", name)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(String::class.java)
            .returnResult()

        Assertions.assertEquals("Hello, ${name.uppercase(Locale.getDefault())}", result.responseBody)
    }
}