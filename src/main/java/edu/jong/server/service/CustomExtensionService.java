package edu.jong.server.service;

import java.util.List;

import edu.jong.server.model.ExtensionInfo;

public interface CustomExtensionService {

	ExtensionInfo add(String extension);

	void remove(long extensionNo);

	List<ExtensionInfo> search();
	
}
