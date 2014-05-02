package com.week.rh.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.portlet.PortletSession;

import org.primefaces.event.FileUploadEvent;

import com.liferay.faces.bridge.model.UploadedFile;
import com.liferay.faces.util.logging.Logger;
import com.liferay.faces.util.logging.LoggerFactory;
import com.week.rh.dto.UploadedFileWrapper;

@ManagedBean
@RequestScoped
public class LayoutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = LoggerFactory.getLogger(LayoutorBean.class);
	
	private UploadedFile uploadedFile;
	
	@PostConstruct
	public void init() {

	}

	public LayoutorBean() {

	}
	
	
	
	
	public void handleFileUpload(FileUploadEvent event) {
		ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
		PortletSession portletSession = (PortletSession) externalContext.getSession(false);
		String uniqueFolderName = portletSession.getId();
		org.primefaces.model.UploadedFile uploadedFile = event.getFile();
		UploadedFileWrapper uploadedFileWrapper = new UploadedFileWrapper(uploadedFile, UploadedFile.Status.FILE_SAVED,
				uniqueFolderName);
		logger.debug("Received fileName=[{0}] absolutePath=[{1}]", uploadedFileWrapper.getName(),
			uploadedFileWrapper.getAbsolutePath());
	}
	
	
	
	
	
	
	
	
	
	
	
	
}