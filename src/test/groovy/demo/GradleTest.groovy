package demo

import groovy.transform.AutoClone

import org.junit.After
import org.junit.Before
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.IntegrationTest
import org.springframework.boot.test.SpringApplicationContextLoader
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration

import spock.lang.Specification
import demo.domain.Customer
import demo.repository.CustomerRepository

//http://stackoverflow.com/questions/24405727/integration-test-with-spring-boot-and-spock
@ContextConfiguration(loader = SpringApplicationContextLoader.class, classes = Application.class)
@WebAppConfiguration
@IntegrationTest
class GradleTest extends Specification {
	
	@Autowired
	private CustomerRepository customerRepo
	
	@Autowired
	private MongoTemplate mongoTemplate
	
	SpecListener listener = Mock()
	
	@Before
	public void setup() {
	  mongoTemplate.dropCollection("customer")
	  customerRepo.save(new Customer(firstName:"Sam", lastName:"Tse"))
	}
  
	@After
	public void teardown() {
	  mongoTemplate.dropCollection("customer")
	}
	
	def "length of Spock's and his friends' names"() {
		expect:
		name.size() == length

		where:
		name     | length
		"Spock"  | 5
		"Kirk"   | 4
		"Scotty" | 6
	}
	
	def "clone a spec"() {
		setup:
		Spec spec = new Spec()
		SpecRepo repo = new SpecRepo()
		repo.listener = listener
		listener.cloneEvent(_) >>  {println "clone"} 
		//repo.clone(spec) << spec
		
		when:
		Spec newSpec = repo.clone(spec)
		
		then:		
		newSpec != null
		1 * listener.cloneEvent(_)
		newSpec.id != spec.id		
	}
	
	def "spring beans should not be null"() {
		expect:
		customerRepo != null
		mongoTemplate != null
	}
}

@AutoClone
class Spec {
	
	Integer id = 0
	String content
}

class SpecRepo {
	
	SpecListener listener
	
	Spec clone(Spec spec) {
		Spec newSpec = spec.clone()
		newSpec.id += 1
		listener.cloneEvent(spec)
		return newSpec
	}
	
}


interface SpecListener {
	
	String cloneEvent(def spec)
}