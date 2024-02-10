package com.tech.service.controller;

public class SchemaTabWrapper {
    public long tabid;
    public String tabname;
    public String logo;
    public String tabkey;    

    public SchemaTabWrapper(long tabid, String tabname, String tabkey, String logo){
        this.tabid = tabid;
        this.tabname =tabname;
        this.logo = logo;
        this.tabkey = tabkey;
    }
}
