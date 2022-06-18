package edu.jong.server.service;

import java.util.List;

import edu.jong.server.enums.FixedExtensionState;
import edu.jong.server.model.ExtensionInfo;

public interface FixedExtensionService {

	ExtensionInfo add(String extension, FixedExtensionState state);

	ExtensionInfo checkOrUncheck(long extensionNo, FixedExtensionState state);

	List<ExtensionInfo> search(FixedExtensionState state);

}
