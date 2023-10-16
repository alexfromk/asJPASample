package ua.ak.sample.jpa.service;

import ua.ak.sample.jpa.repository.TutorialRepository;

public class OneService
{
	TutorialRepository tutorialRepository;

	/**
	 * @return the tutorialRepository
	 */
	public TutorialRepository getTutorialRepository()
	{
		return tutorialRepository;
	}

	/**
	 * @param tutorialRepository the tutorialRepository to set
	 */
	public void setTutorialRepository(TutorialRepository tutorialRepository)
	{
		this.tutorialRepository = tutorialRepository;
	}
	
	
}
