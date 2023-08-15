package com.example.introductory.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "uuid")
    private UUID uuid;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "friend",
            joinColumns = @JoinColumn(name = "first_client_id", referencedColumnName = "uuid"),
            inverseJoinColumns = @JoinColumn(name = "second_client_id", referencedColumnName = "uuid"))
    private Set<Client> friend;

    /*      GETTERS     */
    public UUID getUuid() {
        return uuid;
    }

    public Set<Client> getFriend() {
        return friend;
    }

    /*      SETTERS     */
    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void setFriend(Set<Client> friend) {
        this.friend = friend;
    }
}
