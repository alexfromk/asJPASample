package ua.ak.sample.jpa.service;

import java.util.List;
import java.util.Optional;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ua.ak.sample.jpa.model.Tutorial;
import ua.ak.sample.jpa.repository.TutorialRepository;

public class OneService
{
	TutorialRepository tutorialRepository;
	PlatformTransactionManager transactionManager;
	
	/**
	 * @param published
	 * @return
	 * @see ua.ak.sample.jpa.repository.TutorialRepository#findByPublished(boolean)
	 */
	public List<Tutorial> findByPublished(boolean published)
	{
		return tutorialRepository.findByPublished(published);
	}

	/**
	 * @param title
	 * @return
	 * @see ua.ak.sample.jpa.repository.TutorialRepository#findByTitleContaining(java.lang.String)
	 */
	public List<Tutorial> findByTitleContaining(String title)
	{
		return tutorialRepository.findByTitleContaining(title);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.ListCrudRepository#findAll()
	 */
	public List<Tutorial> findAll()
	{
		return tutorialRepository.findAll();
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	public Optional<Tutorial> findById(Long id)
	{
		return tutorialRepository.findById(id);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#existsById(java.lang.Object)
	 */
	public boolean existsById(Long id)
	{
		return tutorialRepository.existsById(id);
	}

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

	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Transactional
	public <S extends Tutorial> S save(S entity)
	{
		return tutorialRepository.save(entity);
	}

	/**
	 * Explicit run transaction
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	public <S extends Tutorial> S saveExt(S entity)
	{
		DefaultTransactionDefinition txDef=new DefaultTransactionDefinition(TransactionDefinition.PROPAGATION_REQUIRED);
		txDef.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
		txDef.setName("AKtestTrans");
        
		TransactionStatus transaction = transactionManager.getTransaction(txDef);
		try
		{
			S result = tutorialRepository.save(entity);
			transactionManager.commit(transaction);		
			return result;
		}
		catch (TransactionException e)
		{
			transactionManager.rollback(transaction);
			e.printStackTrace();
			throw e;
		}
	}	

	/**
	 * @return the transactionManager
	 */
	public PlatformTransactionManager getTransactionManager()
	{
		return transactionManager;
	}

	/**
	 * @param transactionManager the transactionManager to set
	 */
	public void setTransactionManager(PlatformTransactionManager transactionManager)
	{
		this.transactionManager = transactionManager;
	}
	
	
}
