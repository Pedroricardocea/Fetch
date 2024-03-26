package com.example.fetch;

public class JsonData {
    private int id;
    private int listID;
    private String name;

    public JsonData(int id, int listID, String name) {
        this.id = id;
        this.listID = listID;
        this.name = name;

    }

    public int getID(){
        return id;
    }

    public void setId(char id){
        this.id = id;
    }

    public int getListID(){
        return listID;
    }

    public void setListID(int listID){
        this.listID = listID;
    }

    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name = name;
    }


}
