package com.cy.service.work;

import com.cy.common.basics.service.AbstractBasicsServiceImpl;
import com.cy.dao.work.IContentDao;
import com.cy.entity.work.Content;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/*
 * @program: mtes
 * @description: 评价内容业务层
 * @author: Cy
 * @create: 2019-03-26
 **/
@Service
public class ContentServiceImpl extends AbstractBasicsServiceImpl<Content, Integer> implements IContentService {

    private IContentDao contentDao;

    @Autowired
    public void setContentDao(IContentDao contentDao) {
        this.contentDao = contentDao;
    }
}
