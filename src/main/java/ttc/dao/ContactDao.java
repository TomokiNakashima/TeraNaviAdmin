package ttc.dao;

import java.util.List;
import java.util.ArrayList;
import java.util.Map;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import ttc.util.MySqlConnectionManager;
import ttc.bean.Bean;
import ttc.bean.TopicBean;
import ttc.bean.ContactBean;
import ttc.exception.integration.IntegrationException;

public class ContactDao implements AbstractDao{

    public Bean read(Map map)throws IntegrationException{
        ContactBean cb=new ContactBean();
        PreparedStatement pst = null;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            String sql = "SELECT * FROM contacts where contact_id=? ";

            pst = cn.prepareStatement(sql);
            pst.setString(1,(String)map.get("conId"));



            ResultSet rs = pst.executeQuery();

            rs.next();
			cb.setId(rs.getString(1));
            cb.setUserName(rs.getString(2));
            cb.setTitle(rs.getString(3));
            cb.setContactBody(rs.getString(4));
            cb.setDate(rs.getString(5));
            cb.setReadFlag(rs.getString(6));
            cb.setAddress(rs.getString(7));
            cb.setCategory(rs.getString(8));

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
        return cb;

    }

    public int update(Map map)throws IntegrationException{
    PreparedStatement pst=null;
        int result=0;
        try{
            Connection cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("update contacts set read_flag=1 where contact_id=?");

            pst = cn.prepareStatement(new String(sql));
            pst.setString(1,(String)map.get("conId"));
            result = pst.executeUpdate();

            System.out.println("\t処理件数 : "+result);

        }catch(SQLException e){
            MySqlConnectionManager.getInstance().rollback();
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

    public int insert(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();

            String sql="insert into contact(policy_date,policy_body) values(sysdate(),?)";

            pst = cn.prepareStatement(sql);

            pst.setString(1, (String)map.get("policy"));


            result = pst.executeUpdate();

        }catch(SQLException e){
            MySqlConnectionManager.getInstance().rollback();
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

    public List readAll(Map map)throws IntegrationException{
        List result = new ArrayList();
        PreparedStatement pst = null;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            StringBuffer sql = new StringBuffer();
            sql.append("select contact_id,contact_user_name,contact_title,contact_body,");
            sql.append("contact_date,read_flag,contact_address,contact_category ");
            sql.append("from contacts order by contact_date desc");

            pst = cn.prepareStatement(new String(sql));

            ResultSet rs = pst.executeQuery();

            while(rs.next()){
                ContactBean con = new ContactBean();
                con.setId(rs.getString(1));
                con.setUserName(rs.getString(2));
                con.setTitle(rs.getString(3));
                con.setContactBody(rs.getString(4));
                con.setDate(rs.getString(5));
                con.setReadFlag(rs.getString(6));
                con.setAddress(rs.getString(7));
                con.setCategory(rs.getString(8));
                result.add(con);
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
