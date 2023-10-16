package ua.ak.sample.jpa.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import ua.ak.sample.jpa.service.OneService;
import ua.ak.sample.jpa.repository.TutorialRepository;

@Configuration
@EnableJpaRepositories(basePackages = "ua.ak.sample.jpa.repository")
class MyConfig
{
	@Autowired
	TutorialRepository repo;
	
	@Bean
	public OneService oneService() //TutorialRepository repo)
	{
		OneService serv=new OneService();
		serv.setTutorialRepository(repo);
		return serv;
	}
}
