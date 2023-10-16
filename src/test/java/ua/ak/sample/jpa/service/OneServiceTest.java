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
		List<Tutorial> list = oneService.findAll();
		assertNotNull(list);
		oneService.save(new Tutorial("aaa","now is "+new Date(),true));
		list = oneService.findAll();
		assertTrue(list.size()>0);
		System.out.println("Sum size: "+list.size()+" first item:\n"+list.get(0));
//		//
		long lastId = list.get(list.size()-1).getId();
		Optional<Tutorial> itemById = oneService.findById(lastId);
		System.out.println("itemById, lastId: "+lastId+" present: "+itemById.isPresent());
		if(itemById.isPresent())System.out.println("itemById: "+itemById.get());
//		
		
	}

	@Test
	void testSaveExtTutorialRepository()
	{
		assertNotNull(oneService);
		List<Tutorial> list = oneService.findAll();
		assertNotNull(list);
		oneService.saveExt(new Tutorial("ext","now is "+new Date(),true));
		list = oneService.findAll();
		assertTrue(list.size()>0);
		System.out.println("Sum size: "+list.size()+" first item:\n"+list.get(0));
//		//
		long lastId = list.get(list.size()-1).getId();
		Optional<Tutorial> itemById = oneService.findById(lastId);
		System.out.println("itemById, lastId: "+lastId+" present: "+itemById.isPresent());
		if(itemById.isPresent())System.out.println("itemById: "+itemById.get());
//		
		
	}
}
