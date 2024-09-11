package model;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<Item> items;

    public Cart(List<Item> items) {
        this.items = items;
    }

    public Cart() {
        items = new ArrayList<>();
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Item getItemById(String id) {
        for (Item i : items) {
            if (i.getProduct().getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public int getQuantityById(String id) {
        return getItemById(id).getQuantity();
    }

    public void addItem(Item t) {
        if (getItemById(t.getProduct().getId()) != null) {
            Item i = getItemById(t.getProduct().getId());
            i.setQuantity(i.getQuantity() + t.getQuantity());
        } else {
            items.add(t);
        }
    }

    public void removeItem(String i) {
        if (getItemById(i) != null) {
            items.remove(getItemById(i));
        }
    }

    public int getTotalMoney() {
        int t = 0;
        for (Item i : items) {
            t += i.getQuantity() * i.getPrice();
        }
        return t;
    }

    private Product getProductById(String id, List<Product> list) {
        for (Product i : list) {
            if (i.getId().equals(id)) {
                return i;
            }
        }
        return null;
    }

    public Cart(String txt, List<Product> list) {
        items = new ArrayList<>();
        try {
            if (txt != null && txt.length() != 0) {
                String[] s = txt.split("-");
                for (String i : s) {
                    String[] n = i.split(":");
                    String id = n[0];
                    int quantity = Integer.parseInt(n[1]);
                    if (CheckPExit(id)) {
                        Item t = getItemById(id);
                        t.setQuantity(t.getQuantity() + quantity);
                    } else {
                        Product p = getProductById(id, list);
                        Item t = new Item(p, quantity, p.getPrice());
                        addItem(t);
                    }
                }
            }
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean CheckPExit(String id) {
        for (Item i : items) {
            if (i.getProduct().getId().equals(id)) {
                return true;
            }
        }
        return false;
    }
}
