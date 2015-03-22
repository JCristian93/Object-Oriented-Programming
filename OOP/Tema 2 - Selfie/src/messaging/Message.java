package messaging;

import types.TaskType;

public abstract class Message {
	private TaskType taskType;
	private int messageId;
	public static int valoare_generate = 0;
	public Message(TaskType taskType) {
		super();
		this.taskType = taskType;
		generateId();
	}
	
	public void generateId() {
		//TODO: generate unique ids for Message
		//generez numere random ce pot avea orice valoare din int , sansa sa fie acelasi id e f f f mica
		//!in mod evident pot fi si valori negative
		//Random random = new Random(System.currentTimeMillis());
		this.messageId = valoare_generate;
		valoare_generate++;
	}
	
	public void setTaskType(TaskType taskType) {
		this.taskType = taskType;
	}

	public TaskType getTaskType() {
		return taskType;
	}

	public int getId() {
		return messageId;
	}
	
	
}
