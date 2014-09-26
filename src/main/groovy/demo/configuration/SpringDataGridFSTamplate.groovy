package demo.configuration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.data.mongodb.MongoDbFactory
import org.springframework.data.mongodb.core.SimpleMongoDbFactory
import org.springframework.data.mongodb.core.convert.MappingMongoConverter
import org.springframework.data.mongodb.core.convert.MongoConverter
import org.springframework.data.mongodb.core.mapping.MongoMappingContext
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.gridfs.GridFsCriteria
import org.springframework.data.mongodb.gridfs.GridFsOperations
import org.springframework.data.mongodb.gridfs.GridFsResource
import org.springframework.data.mongodb.gridfs.GridFsTemplate

import com.mongodb.Mongo
import com.mongodb.gridfs.GridFSDBFile

public class SpringDataGridFSTamplate {

	static final String TEST_FILE = "test.txt"


	@Bean
	public static MongoDbFactory mongoDbFactory() throws Exception {
		Mongo mongo = new Mongo()
		String databaseName = "spring-mongo-sample-files"
		MongoDbFactory mongoDbFactory = new SimpleMongoDbFactory(mongo, databaseName)
		return mongoDbFactory
	}
	@Bean
	public static GridFsTemplate gridFsTemplate() throws Exception {
		MongoDbFactory dbFactory = mongoDbFactory()
		MongoConverter converter = mongoConverter()
		GridFsTemplate gridFsTemplate = new GridFsTemplate(dbFactory, converter)
		return gridFsTemplate
	}
	@Bean
	public static MongoConverter mongoConverter() throws Exception {
		MongoMappingContext mappingContext = new MongoMappingContext()
		MappingMongoConverter mappingMongoConverter = new MappingMongoConverter(
				mongoDbFactory(), mappingContext)
		return mappingMongoConverter
	}
//	public static void main(String[] args) {
//		new SpringDataGridFSTamplate().read()
//	}

	void read() {
		InputStream is = SpringDataGridFSTamplate.getResourceAsStream(TEST_FILE)
		assert is != null

		gridFsTemplate().store(is, TEST_FILE)
		List<GridFSDBFile> result = gridFsTemplate().find(
				new Query().addCriteria(GridFsCriteria.whereFilename().is(TEST_FILE))
				)

		println result.size()
		result.each { println "${it.filename}:  ${it.getInputStream().getText()}" }
	}
}