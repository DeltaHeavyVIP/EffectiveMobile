package com.example.introductory.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    @Column(name = "title")
    private String title;

    @Column(name = "text")
    private String text;

    @Lob
    @Column(name = "image")
    private byte[] image;

    @Column(name = "create_date")
    private Date createDate;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
    private Client client;

    /*      CONSTRUCTORS     */
    public Post() {
    }

    public Post(UUID uuid, String title, String text, byte[] image, Client client, Date createDate) {
        this.uuid = uuid;
        this.title = title;
        this.text = text;
        this.image = image;
        this.client = client;
        this.createDate = createDate;
    }

    /*      GETTERS     */
    public UUID getUuid() {
        return uuid;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public byte[] getImage() {
        return image;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Client getClient() {
        return client;
    }

    /*      SETTERS     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
