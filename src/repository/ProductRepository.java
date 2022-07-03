package repository;

import java.util.List;

public interface ProductRepository<T> extends FileRepository<T>  {
	boolean writeToFile(List<T> list, final String fileName);
	
	List<T> readDataFromFile(final String fileName);
	
	
}
