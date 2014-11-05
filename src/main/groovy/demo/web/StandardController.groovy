package demo.web

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import demo.domain.Course
import demo.domain.Customer
import demo.domain.Datatype
import demo.domain.Standard
import demo.repository.StandardRepository
import demo.web.exception.PatientNotFound

@RestController
@RequestMapping("/std")
class StandardController {

	@Autowired
	StandardRepository standardRepo

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	String create() {
		Standard std = new Standard(
				id: "MR02.05",
				name: "MR 02.05"
				)

		std.dataypes.add(getDT())

		standardRepo.save(std)
	}

	@RequestMapping(value = "/addDt", method = RequestMethod.GET)
	Standard addDT() {
		Standard std = standardRepo.findAll().get(0)
		std.dataypes.add(getDT())		
		standardRepo.save(std)
		return std 
	}
	
	private getDT() {
		return new Datatype(
				id: "CD.LAB",
				businessName: "Coded Value (LAB)",
				)
	}
}
