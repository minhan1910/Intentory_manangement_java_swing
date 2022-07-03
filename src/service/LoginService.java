package service;

import java.util.List;
import java.util.Map;

public interface LoginService {
	List<Map<String, String>> findAll(final String fileName);
}
