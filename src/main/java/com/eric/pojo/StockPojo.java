package com.eric.pojo;

import java.util.List;

public class StockPojo {
    private List<StockOnePojo> stockOnePojoList;
    private List<StockTwoPojo> stockTwoPojoList;

    public List<StockOnePojo> getStockOnePojoList() {
        return stockOnePojoList;
    }

    public void setStockOnePojoList(List<StockOnePojo> stockOnePojoList) {
        this.stockOnePojoList = stockOnePojoList;
    }

    public List<StockTwoPojo> getStockTwoPojoList() {
        return stockTwoPojoList;
    }

    public void setStockTwoPojoList(List<StockTwoPojo> stockTwoPojoList) {
        this.stockTwoPojoList = stockTwoPojoList;
    }
}
