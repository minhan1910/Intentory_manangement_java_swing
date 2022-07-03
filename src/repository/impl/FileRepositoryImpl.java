package repository.impl;

import java.io.File;

import repository.FileRepository;

public  class FileRepositoryImpl<T> implements FileRepository<T> {
	@Override
	public boolean removeFile(final String fileName) {
		File oldFile = new File(fileName);
		if (oldFile.isFile() && oldFile.exists())
			if (oldFile.delete())
				return true;
		return false;
	}
}
