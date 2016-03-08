/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import ttc.exception.business.BusinessLogicException;

/**
 *
 * @author Masaki
 */
public class PasswordSaffer {
//	ストレッチ数をカウントするための定数
	private static int STRETCH_COUNT = 10000;
	
	
//	文字列からsha256のハッシュ値を生成するメソッド 内部から呼び出すメソッドなのでprivate
	private static String getSha256(String target) throws BusinessLogicException{
		MessageDigest md = null;
		StringBuffer buff = new StringBuffer();
		try{
			md = MessageDigest.getInstance("SHA-256");
			md.update(target.getBytes());
			byte[] digest = md.digest();
			
			for(int i = 0;i < digest.length;i++){
				buff.append(String.format("%02x",digest[i]));
			}
		}catch(NoSuchAlgorithmException e){
			throw new BusinessLogicException(e.getMessage(), e);
		}
		
		return buff.toString();
	}
	
//	salt + ストレッチしたパスワードを取得する処理
	public static String getStretchedPassword(String password,String loginId)throws BusinessLogicException{
		String salt = getSha256(loginId);
		String hash = "";
		for(int i = 0;i < STRETCH_COUNT;i++){
			hash = getSha256(hash + salt + password);
		}
		
		return hash;
	}
}
