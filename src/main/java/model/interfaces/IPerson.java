package model.interfaces;


public interface IPerson {
     String getName();

     int getId();

     void setName(String name);

     IBand getBand();

     void setBand(IBand band);
}
