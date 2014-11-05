package demo.domain

import javax.validation.constraints.NotNull

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.index.Indexed
import org.springframework.data.mongodb.core.mapping.Document

@Document	
class Datatype {
	
	@Indexed(unique=true)
	String id
	
	String name
	
	String businessName
	
	Boolean isAbstract
	
}
