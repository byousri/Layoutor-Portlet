package com.week.vo;

import java.util.ArrayList;
import java.util.List;

public class Rubrique {
    
    private String         name;
    
    private Long           idGroup;
    
    private Long           IdParent;
    
    private String         type;
    
    private String         idTemplate;
    
    private List<Rubrique> filles = new ArrayList<Rubrique>();
    
    public String getIdTemplate() {
        return idTemplate;
    }
    
    public void setIdTemplate(String idTemplate) {
        this.idTemplate = idTemplate;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public Long getIdGroup() {
        return idGroup;
    }
    
    public void setIdGroup(Long idGroup) {
        this.idGroup = idGroup;
    }
    
    public Long getIdParent() {
        return IdParent;
    }
    
    public void setIdParent(Long idParent) {
        IdParent = idParent;
    }
    
    public String getType() {
        return type;
    }
    
    public void setType(String type) {
        this.type = type;
    }
    
    public List<Rubrique> getFilles() {
        return filles;
    }
    
    public void setFilles(List<Rubrique> filles) {
        this.filles = filles;
    }
    
}
