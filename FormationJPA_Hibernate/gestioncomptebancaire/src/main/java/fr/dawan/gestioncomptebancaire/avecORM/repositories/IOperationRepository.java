package fr.dawan.gestioncomptebancaire.avecORM.repositories;

import java.time.LocalDate;

import fr.dawan.gestioncomptebancaire.avecORM.entities.Operation;

public interface IOperationRepository extends IGenericRepository<Operation, Long>{

	Operation findByDateOperation(LocalDate dateOperation);
	
}
