package fr.xebia.katas.gildedrose;

import java.util.ArrayList;
import java.util.List;

public class Inn {
    private final List<Item> items;

    public Inn() {
        items = new ArrayList<>();
        items.add(new Item("+5 Dexterity Vest", 10, 20));
        items.add(new Item("Aged Brie", 2, 0));
        items.add(new Item("Elixir of the Mongoose", 5, 7));
        items.add(new Item("Sulfuras, Hand of Ragnaros", 0, 80));
        items.add(new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20));
        items.add(new Item("Conjured Mana Cake", 3, 6));
    }

    public List<Item> getItems() {
        return items;
    }

    public void updateQuality() {
        for (Item item : items) {
            if (item.getName().startsWith("S")) { // Sulfuras, Hand of Ragnaros
                continue;
            }
            decrementSellIn(item);
            switch (item.getName().charAt(0)) {
            case 'A': // Aged Brie
                incrementQuality(item);
                if (item.getSellIn() < 0) {
                    incrementQuality(item);
                }
                break;
            case 'B': // Backstage passes to a TAFKAL80ETC concert
                incrementQuality(item);
                if (item.getSellIn() < 10) {
                    incrementQuality(item);
                }
                if (item.getSellIn() < 5) {
                    incrementQuality(item);
                }
                if (item.getSellIn() < 0) {
                    item.setQuality(0);
                }
                break;
            case 'C': // Conjured
                decrementQuality(item);
            default:
                decrementQuality(item);
                if (item.getSellIn() < 0) {
                    decrementQuality(item);
                }
            }
        }

    }

    private void decrementSellIn(Item item) {
        item.setSellIn(item.getSellIn() - 1);
    }

    private void incrementQuality(Item item) {
        if (item.getQuality() < 50) {
            item.setQuality(item.getQuality() + 1);
        }
    }

    private void decrementQuality(Item item) {
        if (item.getQuality() > 0) {
            item.setQuality(item.getQuality() - 1);
        }
    }

}
