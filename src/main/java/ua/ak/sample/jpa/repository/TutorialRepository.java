package ua.ak.sample.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import ua.ak.sample.jpa.model.Tutorial;
@Transactional
public interface TutorialRepository extends JpaRepository<Tutorial, Long> {
	  List<Tutorial> findByPublished(boolean published);

	  List<Tutorial> findByTitleContaining(String title);
	}
