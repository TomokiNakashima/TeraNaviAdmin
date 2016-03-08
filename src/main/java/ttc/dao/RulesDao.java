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
import ttc.bean.RulesBean;
import ttc.bean.UserBean;
import ttc.exception.integration.IntegrationException;

public class RulesDao implements AbstractDao{


    public int update(Map map)throws IntegrationException{
        return 0;
    }

    public int insert(Map map)throws IntegrationException{
        PreparedStatement pst = null;
        int result = 0;
        try{
            Connection cn = null;
            cn = MySqlConnectionManager.getInstance().getConnection();
            MySqlConnectionManager.getInstance().beginTransaction();

            String sql="insert into rules(rule_date,rule_body) values(sysdate(),?)";

            pst = cn.prepareStatement(sql);

            pst.setString(1, (String)map.get("rule"));


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
        PreparedStatement pst = null;
		List result = new ArrayList();

		try{

			Connection cn = null;
			cn = MySqlConnectionManager.getInstance().getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append("select rule_id,rule_date,rule_body ");
			sql.append("from rules order by rule_date desc");

			pst = cn.prepareStatement(new String(sql));
			ResultSet rs = pst.executeQuery();

			while(rs.next()){
				RulesBean bean = new RulesBean();
				bean.setId(rs.getString(1));
				bean.setDate(rs.getString(2));
				bean.setBody(rs.getString(3));

				result.add(bean);
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

    public Bean read(Map map)throws IntegrationException{
        PreparedStatement pst = null;
		RulesBean bean = null;

		try{
			Connection cn = null;
			cn = MySqlConnectionManager.getInstance().getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append("select rule_id,rule_date,rule_body ");
			sql.append("from rules");

			boolean flag = map.containsKey("where");

			if(flag){
				sql.append(map.get("where"));

			}

			sql.append(" order by rule_date desc");

			pst = cn.prepareStatement(new String(sql));

			if(flag){
				pst.setString(1,(String)map.get("value"));
			}

			ResultSet rs = pst.executeQuery();
			rs.next();

			bean = new RulesBean();
			bean.setId(rs.getString(1));
			bean.setDate(rs.getString(2));
			bean.setBody(rs.getString(3));



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
		return bean;
    }

}
