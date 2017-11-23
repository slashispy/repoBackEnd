package py.edu.ucsa.rest.api.util;

public class ErrorDTO {
	private String mensajeError;

	public ErrorDTO(String mensajeError) {
		super();
		this.mensajeError = mensajeError;
	}

	public String getMensajeError() {
		return mensajeError;
	}
	
	
}
