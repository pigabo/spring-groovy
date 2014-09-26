package demo.domain

import javax.validation.constraints.NotNull

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document	
class Course {
	
	@Id
	String id
	
	String name
	
	@NotNull
	String teacher
}
