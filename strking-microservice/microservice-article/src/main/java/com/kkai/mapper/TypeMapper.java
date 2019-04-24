package com.kkai.mapper;

import com.kkai.entity.CountUnderTypeArticleVo;
import com.kkai.entity.TypeVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Kkai on 2018/3/25.
 */
@Mapper
public interface TypeMapper {
    List<TypeVo> findTypeList();

    TypeVo getTypeContent(@Param("type_id") Integer type_id);

    List<CountUnderTypeArticleVo> countUnderArticle();

    int delType(@Param("id") Integer id);

    int addType(@Param("type_content") String type_content);
}
