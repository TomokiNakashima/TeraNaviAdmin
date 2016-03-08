package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ttc.bean.ArticleBean;
import ttc.bean.Bean;

import ttc.exception.integration.IntegrationException;

import ttc.util.MySqlConnectionManager;

public class ArticleSearchDao implements AbstractDao{
    public int insert(Map map)throws IntegrationException{
        return 0;
    }
    public int update(Map map)throws IntegrationException{
        return 0;
    }
    public Bean read(Map map)throws IntegrationException{
        return null;
    }

    public List readAll(Map map)throws IntegrationException{
        List result = new ArrayList();
        PreparedStatement pst = null;
        String[] keywords = null;
        for(int i=0;i<map.size();i++){
            keywords[i]=(String)map.get("keyword"+i);
        }

        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("select article_id,article_title,article_body,article_created_date ");
            sql.append("from articles where article_body like '%?%'");
            for(int i=1;i<keywords.length;i++){
                sql.append(" and");
                sql.append(" article_body like ");
                sql.append("'%");
                sql.append("?");
                sql.append("%'");
            }
            sql.append(" or");
            sql.append(" article_title like '%?%'");
            for(int i=1;i<keywords.length;i++){
                sql.append(" and");
                sql.append(" article_title ");
                sql.append("'%");
                sql.append("?");
                sql.append("%'");
            }
            for(int i=0;i<keywords.length;i++){
                pst.setString(i+1,keywords[i]);
            }

            pst = cn.prepareStatement(new String(sql));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                ArticleBean article = new ArticleBean();
                article.setArticleId(rs.getString(1));
                article.setTitle(rs.getString(2));
                article.setArticleBody(rs.getString(3));
                article.setCreatedDate(rs.getString(4));
                result.add(article);
            }

        }catch(SQLException e){
            throw new IntegrationException(e.getMessage(),e);
        }finally{
            try{
                if(pst!=null){
                    pst.close();
                }
            }catch(SQLException e){
                throw new IntegrationException(e.getMessage(),e);
            }
        }

        return result;
    }
}
