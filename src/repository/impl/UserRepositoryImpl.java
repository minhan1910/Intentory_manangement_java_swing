package repository.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import repository.UserRepository;
import repository.entity.UserEntity;

public class UserRepositoryImpl extends FileRepositoryImpl<UserEntity> implements UserRepository<UserEntity> {

	@Override
	public boolean writeToFile(List<UserEntity> list, String fileName) {
		File file = new File(fileName);
		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (file.exists()) {
			try (FileOutputStream fos = new FileOutputStream(file);
					ObjectOutputStream oos = new ObjectOutputStream(fos);) {
				oos.writeObject(list);
				System.out.println("Save file successfully !!");
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserEntity> readDataFromFile(String fileName) {
		List<UserEntity> list = new ArrayList<>();
		File file = new File(fileName);

		if (file.exists()) {
			try (FileInputStream fis = new FileInputStream(file); ObjectInputStream ois = new ObjectInputStream(fis);) {
				list = (List<UserEntity>) ois.readObject();
				System.out.println("Save file successfully !!");
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
