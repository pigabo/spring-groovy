package demo.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import demo.domain.Course
import demo.domain.Customer
import demo.repository.CourseRepository
import demo.repository.CustomerRepository
import demo.web.exception.PatientNotFound

@RestController
class CustomerController {

	@Autowired
	CustomerRepository customerRepo

	@Autowired
	CourseRepository courseRepo

	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	String hello() {
		"hello"
	}
	
//	@RequestMapping(value = "/customer/{index}", method = RequestMethod.GET)
//	Customer customer(@PathVariable("index") Integer index) {
//		return customerRepo.findAll().get(index)
//	}	
	
	@RequestMapping(value = "/notFound", method = RequestMethod.GET)
	String notFound() {
		throw new PatientNotFound()
	}

	@RequestMapping(value = "/deleteAll", method = RequestMethod.GET)
	String deleteAll() {
		customerRepo.deleteAll()
	}

	@RequestMapping(value = "/insert", method = RequestMethod.GET)
	Customer insert() {
		Course math = new Course(name:"Math")
		courseRepo.save(math)
		
		Course science = new Course(name:"Science")
		courseRepo.save(science)

		def sam = new Customer(firstName:"Sam", lastName:"Tse")
		sam.courses.add(math)
		sam.courses.add(science)

		customerRepo.save(sam)
		
		return sam
	}

	@RequestMapping(value = "/findByLastName/{lastName}", method = RequestMethod.GET)
	List<Customer> findByLastName(@PathVariable("lastName") String lastName) {
		customerRepo.findByLastName(lastName)
	}

	@RequestMapping(value = "/findByLastName2/{lastName}", method = RequestMethod.GET)
	String findByLastName2(@PathVariable("lastName") String lastName, Model model) {
		model.addAttribute("customers", customerRepo.findByLastName(lastName))
		return "customers"
	}
	
	
}
