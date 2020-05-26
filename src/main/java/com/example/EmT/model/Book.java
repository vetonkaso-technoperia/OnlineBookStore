package com.example.EmT.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @NotNull
    @Min(value = 2,message = "Price must be bigger than 2")
    private Float price;

    @NotNull
    @Min(value = 0, message = "Must be positive number")
    private Integer quantity;


    private Long numberofBooks;

    @NotNull
    @ManyToOne
    private Category category;

    @NotNull
    @ManyToOne
    private Author author;

    @JsonIgnore
    @ManyToMany(mappedBy = "books")
    private List<ShoppingCart> shoppingCarts;

//    private MultipartFile image;

    @Column(name = "image")
    @Lob
    private String imageBase64;

    public Book() {}

    public Book(Long id,
                   String name,
                   Long numberOfBooks,
                   Float price,
                   Integer quantity,
                   Category category,
                   Author author) {
        this.id = id;
        this.name = name;
        this.numberofBooks = numberOfBooks;
        this.price = price;
        this.quantity = quantity;
        this.author = author;
        this.category = category;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getnumberofBooks() { return numberofBooks; }

    public void setnumberofBooks(Long numberofBooks) { this.numberofBooks = numberofBooks; }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Book getCategory() { return category; }

    public void setCategory(Book category) { this.category = (Category) category; }

//    public MultipartFile getImage() {
//        return image;
//    }
//
//    public void setImage(MultipartFile image) {
//        this.image = image;
//    }

    public String getImageBase64() {
        return imageBase64;
    }

    public void setImageBase64(String imageBase64) {
        this.imageBase64 = imageBase64;
    }

    public List<ShoppingCart> getShoppingCarts() {
        return shoppingCarts;
    }

    public void setShoppingCarts(List<ShoppingCart> shoppingCarts) {
        this.shoppingCarts = shoppingCarts;
    }
}
