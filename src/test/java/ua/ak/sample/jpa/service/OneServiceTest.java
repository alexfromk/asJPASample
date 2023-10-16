package ua.ak.sample.jpa.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ua.ak.sample.jpa.model.Tutorial;
import java.util.Date;

@SpringBootTest
class OneServiceTest
{
	@Autowired
	OneService oneService;
	
	@BeforeEach
	void setUp() throws Exception
	{
	}

	@AfterEach
	void tearDown() throws Exception
	{
	}

	@Test
	void testGetTutorialRepository()
	{
		assertNotNull(oneService);
		assertNotNull(oneService.getTutorialRepository());
		List<Tutorial> list = oneService.getTutorialRepository().findAll();
		assertNotNull(list);
		oneService.getTutorialRepository().save(new Tutorial("aaa","now is "+new Date(),true));
		list = oneService.getTutorialRepository().findAll();
		assertTrue(list.size()>0);
		System.out.println("Sum size: "+list.size()+" first item:\n"+list.get(0));
		//
		long lastId = list.get(list.size()-1).getId();
		Optional<Tutorial> itemById = oneService.getTutorialRepository().findById(lastId);
		System.out.println("itemById, lastId: "+lastId+" present: "+itemById.isPresent());
		if(itemById.isPresent())System.out.println("itemById: "+itemById.get());
		
		
	}

}
