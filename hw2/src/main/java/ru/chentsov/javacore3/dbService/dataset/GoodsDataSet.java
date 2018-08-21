package ru.chentsov.javacore3.dbService.dataset;

public class GoodsDataSet {

    private long id;
    private long productId;
    private String title;
    private long price;

    public GoodsDataSet(long id, long productId, String title, long price) {
        this.id = id;
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

    @Override
    public String toString() {
        return "GoodsDataSet: " +
                "id = " + id
                + ", productId = " + productId
                + ", title = " + title
                + ", price = " + price;
    }
}
