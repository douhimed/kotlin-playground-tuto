import org.adex.kotlingdemo.KotlingdemoApplication
import org.adex.kotlingdemo.dtos.CourseDto
import org.adex.kotlingdemo.repositories.CourseRepository
import org.adex.kotlingdemo.utils.coursesEntitiesList
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest(classes = [KotlingdemoApplication::class], webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@AutoConfigureWebClient
class CourseControllerIntegrationTests {

    @Autowired
    lateinit var webTestClient: WebTestClient

    @Autowired
    lateinit var courseRepository: CourseRepository;

    val path: String = "/courses"

    @BeforeEach
    fun setUp() {
        courseRepository.deleteAll()
        courseRepository.saveAll(coursesEntitiesList())
    }

    @Test
    @Order(1)
    fun postCourse_ShouldReturnAnId() {

        val actual = CourseDto(null, "Course Test", "Category test")

        val actualResponse = webTestClient
            .post()
            .uri(path)
            .bodyValue(actual)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(Int::class.java)
            .returnResult()
            .responseBody

        Assertions.assertTrue {
            actualResponse != null
        }
    }

    @Test
    @Order(2)
    fun getAll_ShouldReturnsAllCourses() {

        val actual = webTestClient
            .get()
            .uri(path)
            .exchange()
            .expectStatus().isOk
            .expectBodyList(CourseDto::class.java)
            .returnResult()
            .responseBody

        Assertions.assertEquals(3, actual!!.size)
    }

    @Test
    @Order(3)
    fun putCourse_ShouldUpdateCourseAndReturnId() {

        val newCourse = CourseDto(
            null, "Course updated", "Category updated"
        )

        val id = courseRepository.findAll().iterator().next().id

        val actualResponse = webTestClient
            .put()
            .uri("$path/{id}", id)
            .bodyValue(newCourse)
            .exchange()
            .expectStatus().is2xxSuccessful
            .expectBody(Int::class.java)
            .returnResult()
            .responseBody

        Assertions.assertNotNull(actualResponse)
        Assertions.assertEquals(actualResponse, id)
    }

    @Test
    @Order(4)
    fun deleteCourse_ShouldDeleteACourseByIdAndReturnEmptyContent() {

        val courses = courseRepository.findAll()

        webTestClient
            .delete()
            .uri("$path/{id}", courses.iterator().next().id)
            .exchange()
            .expectStatus().isNoContent
    }
}