package com.kkai.service;

import com.kkai.entity.AddRecommendVideoPara;
import com.kkai.entity.ArticleVo;
import com.kkai.entity.RecommendVideoListPara;
import com.kkai.entity.RecommendVideoVo;
import com.kkai.mapper.RecommendVideoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Kkai on 2018/4/10.
 */
@Service
public class RecommendVideoService {
    @Autowired
    private
    RecommendVideoMapper recommendVideoMapper;
    private static Pattern p_img = Pattern.compile("<(iframe)(.*?)(/>|></iframe>|>)");
    private static Pattern p_src = Pattern.compile("(src|SRC)=(\"|\')(.*?)(\"|\')");

    public boolean addRecommendVideo(AddRecommendVideoPara para) {
        Matcher m_img = p_img.matcher(para.getVideo_url());
        boolean result_img = m_img.find();

        //获取到匹配的<img />标签中的内容
        String str_img = m_img.group(2);

        Matcher m_src = p_src.matcher(str_img);
        if (m_src.find()) {
            String videoUrl = m_src.group(3);
            int addnum = recommendVideoMapper.addRecommendVideo(videoUrl,para.getTitle(),para.getFrom_author(),para.getAdd_admin_name());
            if (addnum > 0 ) {
                return true;
            }
        }
        return false;
    }

    public List<RecommendVideoVo> findRecommendVideoList(RecommendVideoListPara req) {
        return recommendVideoMapper.findRecommendVideoList(req.getTitle(),req.getLimit(),req.getOffset());
    }

    public long countRecommendVideoList(RecommendVideoListPara req) {
        return recommendVideoMapper.countRecommendVideoList(req.getTitle());
    }

    public boolean delRecommendById(Integer id) {
        return recommendVideoMapper.delArticleById(id) > 0;
    }
}
