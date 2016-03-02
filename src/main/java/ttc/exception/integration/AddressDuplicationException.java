/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ttc.exception.integration;

/**
 *
 * @author Masaki
 */
public class AddressDuplicationException extends IntegrationException{
	public AddressDuplicationException(String mess,Throwable t){
		super(mess,t);
	}
}
