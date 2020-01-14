package com.cy.service.basic;


import com.cy.common.basics.service.AbstractBasicsServiceImpl;
import com.cy.dao.basic.IStudentDao;
import com.cy.entity.basic.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl extends AbstractBasicsServiceImpl<Student, Integer> implements IStudentService {

    private IStudentDao studentDao;

    @Autowired
    public void setStudentDao(IStudentDao studentDao) {
        this.studentDao = studentDao;
    }

}
