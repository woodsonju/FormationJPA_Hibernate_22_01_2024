package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Operation;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

public class OperationRepository extends GenericRepository<Operation, Long> implements IOperationRepository{

	private static final Logger logger = LoggerFactory.getLogger(OperationRepository.class);
	
	@Override
	public Operation findByDateOperation(LocalDate dateOperation) {
		EntityManager em = createEntityManager();
		Operation operation = null; 
		
		try {
			String jpql_query = "SELECT o FROM Operation o WHERE o.dateOperation = :dateOperation";
			TypedQuery<Operation> query = em.createQuery(jpql_query, Operation.class);
			query.setParameter("dateOperation", dateOperation);
			operation = query.getSingleResult();
			logger.info("Récuperation de l'operation avec succès " + operation);
		} catch (Exception e) {
			logger.error("Echer lors de la récuperation de l'operation  ");
			e.printStackTrace();
		}finally {
			em.close();
		}
		
		return operation;
	}

}
