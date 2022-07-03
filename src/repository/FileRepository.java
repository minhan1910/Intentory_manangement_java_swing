package repository;

public interface FileRepository<T> {
	boolean removeFile(final String fileName);
}
