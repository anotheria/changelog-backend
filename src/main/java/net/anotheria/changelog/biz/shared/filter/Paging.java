package net.anotheria.changelog.biz.shared.filter;

import java.io.Serializable;

/**
 *
 */
public class Paging implements Serializable {

    private static final long serialVersionUID = 7032351730750540236L;

    public static final int DEFAULT_PAGE_SIZE = 10;

    private int page;
    private int itemsOnPage;


    public Paging() {
        this.itemsOnPage = DEFAULT_PAGE_SIZE;
    }

    public Paging(int page) {
        this();
        this.page = page;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getItemsOnPage() {
        return itemsOnPage;
    }

    public void setItemsOnPage(int itemsOnPage) {
        this.itemsOnPage = itemsOnPage;
    }

    public int getOffset() {
        return itemsOnPage * page;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "page=" + page +
                ", itemsOnPage=" + itemsOnPage +
                '}';
    }
}
