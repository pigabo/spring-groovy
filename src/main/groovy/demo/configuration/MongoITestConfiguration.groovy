package demo.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.MongoTemplate
import org.springframework.data.mongodb.core.SimpleMongoDbFactory

import com.mongodb.Mongo

@Configuration
@Profile("testing")
class MongoITestConfiguration {

	@Bean
	MongoDbFactory mongoDbFactory() throws Exception {
		return new SimpleMongoDbFactory(new Mongo(), "spring-mongo-sample-test")
	}

	@Bean
	MongoTemplate mongoTemplate() throws Exception {
		return new MongoTemplate(mongoDbFactory())
	}
}
