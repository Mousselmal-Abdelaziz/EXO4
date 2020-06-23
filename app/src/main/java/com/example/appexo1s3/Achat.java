package com.example.appexo1s3;

public class Achat {
    public String item;
    public double qte;

    //for news details
  public Achat(String item,double qte){  //pass data to layout list view
     this.item=item;
     this.qte=qte;
    }

    public String getItem() {
      return item;
    }

    public double getQte() {
        return qte;
    }
}
