package com.kkai.service;

import com.kkai.entity.CountUnderTypeArticleVo;
import com.kkai.entity.TypeVo;
import com.kkai.mapper.TypeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kkai on 2018/3/25.
 */
@Service
public class TypeService {
    @Autowired
    TypeMapper typeMapper;

    public List<TypeVo> findTypeList() {
        return typeMapper.findTypeList();
    }

    public TypeVo getTypeContent(Integer type_id) {
        return typeMapper.getTypeContent(type_id);
    }

    public List<CountUnderTypeArticleVo> countUnderArticle() {
        return typeMapper.countUnderArticle();
    }

    public boolean delType(Integer id) {
        return typeMapper.delType(id) > 0;
    }

    public boolean addType(String type_content) {
        return typeMapper.addType(type_content) > 0;
    }
}
