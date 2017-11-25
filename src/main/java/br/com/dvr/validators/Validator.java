package br.com.dvr.validators;

public class Validator {
	
	public Validator () {
		
	}
	
	/**
	 * Mensagem de erro ou warning referente o preenchimento do campo 
	 **/
	private String message;
	
	public Validator (String message ){
		this.message = message;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

//	public static List< Validator > newList (String message ) {		
//		return Arrays.asList( new Validator(message));
//	}	

	@Override
	public String toString() {
		return this.message;
	}


	

}
