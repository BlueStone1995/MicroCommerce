package com.ecommerce.microcommercemongo.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

public class Product {
    @Id
    private ObjectId _id;
    private String nom;
    private int prix;
    private int prixAchat;

    public Product() {

    }

    public Product(ObjectId _id, String nom, int prix, int prixAchat) {
        this._id = _id;
        this.nom = nom;
        this.prix = prix;
        this.prixAchat = prixAchat;
    }

    // Getters and Setters
    public String get_id() {
        return _id.toHexString();
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getPrix() {
        return prix;
    }

    public void setPrix(int prix) {
        this.prix = prix;
    }

    public int getPrixAchat() {
        return prixAchat;
    }

    public void setPrixAchat(int prixAchat) {
        this.prixAchat = prixAchat;
    }

    @Override
    public String toString() {
        return "Product{" +
                "_id=" + _id +
                ", nom='" + nom + '\'' +
                ", prix=" + prix +
                ", prixAchat=" + prixAchat +
                '}';
    }
}
