package logic.application;

import java.io.IOException;

/*
 * Interfaccia per la richiesta di un nuovo stage
 */

public interface RequestNewStage {

	void setNewStage(String guiName) throws IOException;

}
