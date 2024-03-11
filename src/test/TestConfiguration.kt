import org.adex.kotlingdemo.KotlingdemoApplication
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan(basePackages = ["org.adex"])
@SpringBootTest(classes = [KotlingdemoApplication::class])
class TestConfig