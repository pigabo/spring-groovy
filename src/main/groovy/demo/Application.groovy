package demo

import org.springframework.beans.factory.annotation.Autowired

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.data.mongodb.core.MongoTemplate

import com.mangofactory.swagger.plugin.EnableSwagger

import demo.domain.Customer
import demo.repository.CourseRepository
import demo.repository.CustomerRepository

@Configuration
@ComponentScan
@EnableSwagger
@EnableAutoConfiguration
class Application implements CommandLineRunner {
	
	@Autowired
	private CustomerRepository customerRepo
	
	@Autowired
	private CourseRepository courseRepo
	
	@Autowired
	private MongoTemplate mongoTemplate
	
    static void main(String[] args) {
        SpringApplication.run(Application, args)		 
    }
	
	@Override
	void run(String... args) throws Exception  {
		
		println mongoTemplate.getDb().getMongo().getAddress()
		
		println "findAll"
		customerRepo.findAll().each { Customer t ->
			println t.toString()
		}
		
		
//		repository.deleteAll()
		
	}
}
