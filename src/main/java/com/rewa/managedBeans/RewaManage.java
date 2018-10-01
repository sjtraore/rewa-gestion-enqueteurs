package com.rewa.managedBeans;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

import org.apache.log4j.Logger;

import com.rewa.hibernate.data.Status;
import com.rewa.spring.service.CommonService;

@ManagedBean
@SessionScoped
public class RewaManage {
	private static final Logger log = Logger.getLogger(RewaManage.class);
	private Map<String, String> statusMap;

	@ManagedProperty("#{commonService}")
	private CommonService commonService;
	
	@PostConstruct
    public void init() {
		statusMap = new HashMap<String, String>();
		for(Status status: commonService.getAllStatus()) {
			statusMap.put(status.getStatus(), status.getStatus());
		}
	}

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	public Map<String, String> getStatusMap() {
		return statusMap;
	}

	public void setStatusMap(Map<String, String> statusMap) {
		this.statusMap = statusMap;
	}
}
