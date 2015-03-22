package messaging;

import types.TaskType;

public class MessageSuccess extends Message{

	public MessageSuccess(TaskType taskType) {
		super(taskType);
	//	System.out.println("Operatie facuta cu success");
	}

}
