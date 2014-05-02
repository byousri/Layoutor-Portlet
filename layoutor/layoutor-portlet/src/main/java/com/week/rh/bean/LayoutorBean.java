package com.week.rh.bean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.apache.log4j.Logger;

@ManagedBean
@RequestScoped
public class LayoutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger(LayoutorBean.class);

	@PostConstruct
	public void init() {

	}

	public LayoutorBean() {

	}
	
}