package mobile.web.webxt.client.data;

import java.util.ArrayList;
import java.util.List;

import com.extjs.gxt.ui.client.data.BaseFilterPagingLoadConfig;

public class MyProcessConfig extends BaseFilterPagingLoadConfig {

	private static final long serialVersionUID = 1L;

	public enum ProcessType {
		QUERY("QRY"), 
		MAINTENANCE("MNT");
		
		private final String processType;
		
		private ProcessType(String processType) {
			this.processType = processType;
		}
		
		public String getProcessType(){
			return processType;
		}
		
	}

	private final String process;

	private ProcessType processType;

	private final String entity;

	private final List<String> lFields;

	public MyProcessConfig(String process, String entity, List<String> lFields) {
		this.process = process;
		this.entity = entity;
		this.lFields = lFields;
	}
	
	public MyProcessConfig(String process) {
		this.process = process;
		this.entity = "";
		this.lFields = new ArrayList<String>();
	}

	public String getProcess() {
		return process;
	}

	public String getEntity() {
		return entity;
	}

	public List<String> getlFields() {
		return lFields;
	}

	public ProcessType getProcessType() {
		return processType;
	}

	public void setProcessType(ProcessType processType) {
		this.processType = processType;
	}

}