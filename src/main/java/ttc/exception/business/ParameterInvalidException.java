/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc.exception.business;

/**
 *
 * @author Masaki
 */
public class ParameterInvalidException extends BusinessLogicException{
	public ParameterInvalidException(String mess,Throwable t){
		super(mess,t);
	}
}
