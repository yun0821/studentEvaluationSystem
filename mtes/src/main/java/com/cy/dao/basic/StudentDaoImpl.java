package com.cy.dao.basic;



import com.cy.common.basics.dao.ManagerBasicsDaoImpl;
import com.cy.entity.basic.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDaoImpl extends ManagerBasicsDaoImpl<Student,Integer> implements IStudentDao   {

}
