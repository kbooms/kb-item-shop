package com.kevinbooms;

import com.kevinbooms.model.Item;
import com.kevinbooms.dao.ItemDao;
import com.kevinbooms.dao.JdbcItemDao;
import com.kevinbooms.dao.OrderDao;

import org.apache.commons.dbcp2.BasicDataSource;
import javax.sql.DataSource;
import java.util.List;

public class ItemShop {

    private ItemDao itemDao;
    private OrderDao orderDao;

    public ItemShop(DataSource dataSource) {
        itemDao = new JdbcItemDao(dataSource);
        // add the OrderDao when JDBC class is implemented
    }
    public static void main(String[] args) {

        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:postgresql://localhost:5432/ItemShop");
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres1");

        ItemShop shop = new ItemShop(dataSource);
        System.out.println("Current connection: " + dataSource.getUrl());
        shop.run();
    }

    public void run() {
        System.out.println("Welcome to the Item Shop");

        // Manual Test: Find Item by ID
        Item itemById = itemDao.getItem(16);
        System.out.println("ID " + itemById.getItemId() + ") " +itemById.getItemName() +
                           " - " + itemById.getItemDescription() + ", " +
                           itemById.getItemValue() + " Gold");

        // Manual Test: Find Items by Type
        List<Item> itemsMatchingType = itemDao.findItemsByType("MAGIC");
        System.out.println("All items matching the \"Magic\" type: ");
        for (Item item : itemsMatchingType) {
            System.out.println(item.getItemName());
        }
    }
}