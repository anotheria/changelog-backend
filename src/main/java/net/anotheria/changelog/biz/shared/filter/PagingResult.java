package net.anotheria.changelog.biz.shared.filter;

import java.io.Serializable;

/**
 *
 */
public class PagingResult implements Serializable {

    private static final long serialVersionUID = 2109173345013817455L;

    private int page;
    private long itemsOnPage;
    private long items;


    public PagingResult(int page, long itemsOnPage, long items) {
        this.page = page;
        this.itemsOnPage = itemsOnPage;
        this.items = items;
    }

    public PagingResult(Paging paging, long totalItems) {
        this(paging.getPage(), paging.getItemsOnPage(), totalItems);
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public long getItemsOnPage() {
        return itemsOnPage;
    }

    public void setItemsOnPage(long itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    public long getItems() {
        return items;
    }

    public void setItems(long items) {
        this.items = items;
    }
}
