package py.edu.ucsa.rest.api.util;

import java.util.Collection;

public class UtilidadesVarias {
	public void initializeCollection(Collection<?> collection) {
		if(collection == null) {
			return;
		}
		collection.iterator().hasNext();
		
	}

}
