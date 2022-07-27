package com.example.clothesshop.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProductDTO extends AbstractDTO<ProductDTO>{
    private String name;
    private BigDecimal price;
    private String description;
    private String slug;
    private Integer status;
    private float avg_rating;
    private String image;

    private CategoryDTO category;

    private List<CommentDTO> comments = new ArrayList<>();

    private List<ProductColorDTO> product_color = new ArrayList<>();

    private List<CollectionDTO> collections = new ArrayList<>();

    private List<PromotionDTO> promotions = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public float getAvg_rating() {
        return avg_rating;
    }

    public void setAvg_rating(float avg_rating) {
        this.avg_rating = avg_rating;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public CategoryDTO getCategory() {
        return category;
    }

    public void setCategory(CategoryDTO category) {
        this.category = category;
    }

    public List<CommentDTO> getComments() {
        return comments;
    }

    public void setComments(List<CommentDTO> comments) {
        this.comments = comments;
    }

    public List<ProductColorDTO> getProduct_color() {
        return product_color;
    }

    public void setProduct_color(List<ProductColorDTO> product_color) {
        this.product_color = product_color;
    }

    public List<CollectionDTO> getCollections() {
        return collections;
    }

    public void setCollections(List<CollectionDTO> collections) {
        this.collections = collections;
    }

    public List<PromotionDTO> getPromotions() {
        return promotions;
    }

    public void setPromotions(List<PromotionDTO> promotions) {
        this.promotions = promotions;
    }
}
