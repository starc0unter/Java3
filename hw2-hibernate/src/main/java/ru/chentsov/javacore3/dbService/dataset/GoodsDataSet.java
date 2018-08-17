package ru.chentsov.javacore3.dbService.dataset;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "goods")
public class GoodsDataSet implements Serializable {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "productId", nullable = false)
    private long productId;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "price", nullable = false)
    private long price;

    @SuppressWarnings("UnusedDeclaration")
    public GoodsDataSet() { }

    public GoodsDataSet(long productId, String title, long price) {
        this.id = -1;
        this.productId = productId;
        this.title = title;
        this.price = price;
    }

    @SuppressWarnings("unused")
    public long getId() {
        return id;
    }

    @SuppressWarnings("unused")
    public long getProductId() {
        return productId;
    }

    public String getTitle() {
        return title;
    }

    public long getPrice() {
        return price;
    }

    @SuppressWarnings("unused")
    public void setId(long id) {
        this.id = id;
    }

    @SuppressWarnings("unused")
    public void setProductId(long productId) {
        this.productId = productId;
    }

    @SuppressWarnings("unused")
    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "GoodsDataSet: " +
                "id = " + id
                + ", productId = " + productId
                + ", title = " + title
                + ", price = " + price;
    }

}
