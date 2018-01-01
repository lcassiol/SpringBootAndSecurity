package com.lcassiol.entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "book")
public class Book implements Serializable{

    private Long id;
    private String name;
    private Author author;
    private Integer year;
    private Date createdAt;
    private Date updatedAt;

    public Book(){

    }


    public void setId(Long id) {
        this.id = id;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setYear(Integer year) {
        this.year = year;
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

    @ManyToOne(fetch = FetchType.EAGER)
    public Author getAuthor() {
        return author;
    }

    @Column(name = "year", nullable = false)
    public Integer getYear() {
        return year;
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
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author=" + author +
                ", year=" + year +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                '}';
    }
}
