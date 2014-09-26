package demo.configuration

import org.springframework.context.annotation.Configuration
import org.springframework.data.rest.core.config.RepositoryRestConfiguration
import org.springframework.data.rest.webmvc.config.RepositoryRestMvcConfiguration

/**
 * This class sets the baseUri to /api. Why would you want to do this? So when you point your browser at http://localhost:8080, you see the web page not the JSON served up by the hypermedia.
 * 
 * @author stse
 *
 */
@Configuration
class CustomizedRestMvcConfiguration  extends RepositoryRestMvcConfiguration {

	@Override
	public RepositoryRestConfiguration config() {
		RepositoryRestConfiguration config = super.config()
		config.setBaseUri(URI.create("/api"))
		return config
	}
}
