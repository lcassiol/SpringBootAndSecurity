package com.lcassiol.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "author")
public class Author implements Serializable {

    private Long id;
    private String name;
    private Date birthDay;
    private String nationality;
    private List<Book> books;
    private Date createdAt;
    private Date updatedAt;

    public Author(){
    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDay(Date birthDay) {
        this.birthDay = birthDay;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    public Long getId() {
        return id;
    }

    @Column(name = "name", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "birthday", nullable = false)
    public Date getBirthDay() {
        return birthDay;
    }

    @Column(name = "nationality", nullable = false)
    public String getNationality() {
        return nationality;
    }

    @OneToMany(mappedBy = "author", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    public List<Book> getBooks() {
        return books;
    }

    @Column(name = "created_at", nullable = false)
    public Date getCreatedAt() {
        return createdAt;
    }

    @Column(name = "updated_at", nullable = false)
    public Date getUpdatedAt() {
        return updatedAt;
    }

    @PrePersist
    public void prePersist(){
        final Date now = new Date();
        createdAt = now;
        updatedAt = now;
    }

    @PreUpdate
    public void preUpdate(){
        updatedAt = new Date();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", birthDay=" + birthDay +
                ", nationality='" + nationality + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
