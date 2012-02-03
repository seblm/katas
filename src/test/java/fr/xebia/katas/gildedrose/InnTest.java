package fr.xebia.katas.gildedrose;

import static org.fest.assertions.Assertions.assertThat;

import java.util.List;

import org.junit.Test;

public class InnTest {

    @Test
    public void should_test_the_truth() throws Exception {
        assertThat(true).isTrue();
    }

    @Test
    public void should_get_items() throws Exception {
        Inn inn = new Inn();
        List<Item> items = inn.getItems();

        assertThat(items).hasSize(6);
        assertItem(items.get(0), "+5 Dexterity Vest", 10, 20);
        assertItem(items.get(1), "Aged Brie", 2, 0);
        assertItem(items.get(2), "Elixir of the Mongoose", 5, 7);
        assertItem(items.get(3), "Sulfuras, Hand of Ragnaros", 0, 80);
        assertItem(items.get(4), "Backstage passes to a TAFKAL80ETC concert", 15, 20);
        assertItem(items.get(5), "Conjured Mana Cake", 3, 6);

        inn.updateQuality();

        assertThat(items).hasSize(6);
        assertItem(items.get(0), "+5 Dexterity Vest", 9, 19);
        assertItem(items.get(1), "Aged Brie", 1, 1);
        assertItem(items.get(2), "Elixir of the Mongoose", 4, 6);
        assertItem(items.get(3), "Sulfuras, Hand of Ragnaros", 0, 80);
        assertItem(items.get(4), "Backstage passes to a TAFKAL80ETC concert", 14, 21);
        assertItem(items.get(5), "Conjured Mana Cake", 2, 4);

        inn.updateQuality();

        assertThat(items).hasSize(6);
        assertItem(items.get(0), "+5 Dexterity Vest", 8, 18);
        assertItem(items.get(1), "Aged Brie", 0, 2);
        assertItem(items.get(2), "Elixir of the Mongoose", 3, 5);
        assertItem(items.get(3), "Sulfuras, Hand of Ragnaros", 0, 80);
        assertItem(items.get(4), "Backstage passes to a TAFKAL80ETC concert", 13, 22);
        assertItem(items.get(5), "Conjured Mana Cake", 1, 2);
    }

    private Inn singleItem(Item item) {
        Inn inn = new Inn();
        List<Item> items = inn.getItems();
        items.clear();
        items.add(item);
        return inn;
    }

    @Test
    public void with_backstage_10_days_quality_increase_by_2() throws Exception {
        Item backstageItem = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 48);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Backstage passes to a TAFKAL80ETC concert", 9, 50);
    }

    @Test
    public void with_backstage_10_days_quality_increase_by_2_until_50() throws Exception {
        Item backstageItem = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Backstage passes to a TAFKAL80ETC concert", 9, 50);
    }

    @Test
    public void with_backstage_5_days_quality_increase_by_3() throws Exception {
        Item backstageItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 47);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Backstage passes to a TAFKAL80ETC concert", 4, 50);
    }

    @Test
    public void with_backstage_5_days_quality_increase_by_3_until_50() throws Exception {
        Item backstageItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 48);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Backstage passes to a TAFKAL80ETC concert", 4, 50);

        backstageItem = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Backstage passes to a TAFKAL80ETC concert", 4, 50);
    }

    @Test
    public void should_decrease_twice_once_sell_date_is_passed() throws Exception {
        Item dummyItem = new Item("dummy", -1, 10);
        Inn inn = singleItem(dummyItem);

        inn.updateQuality();

        assertItem(dummyItem, "dummy", -2, 8);
    }

    @Test
    public void with_passed_backstaged_should_have_quality_zero() throws Exception {
        Item backstageItem = new Item("Backstage passes to a TAFKAL80ETC concert", -1, 10);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Backstage passes to a TAFKAL80ETC concert", -2, 0);
    }

    @Test
    public void with_passed_aged_brie_should_increase_quality_twice() throws Exception {
        Item backstageItem = new Item("Aged Brie", -1, 10);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Aged Brie", -2, 12);
    }

    @Test
    public void with_passed_aged_brie_should_increase_quality_twice_until_50() throws Exception {
        Item backstageItem = new Item("Aged Brie", -1, 48);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Aged Brie", -2, 50);

        backstageItem = new Item("Aged Brie", -1, 49);
        inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Aged Brie", -2, 50);

        inn.updateQuality();

        assertItem(backstageItem, "Aged Brie", -3, 50);
    }

    @Test
    public void with_passed_sulfuras_should_not_decrease_neither_quality_nor_sellin() throws Exception {
        Item backstageItem = new Item("Sulfuras, Hand of Ragnaros", -1, 42);
        Inn inn = singleItem(backstageItem);

        inn.updateQuality();

        assertItem(backstageItem, "Sulfuras, Hand of Ragnaros", -1, 42);
    }

    @Test
    public void with_conjured_quality_degrades_twice() throws Exception {
        Item conjuredItem = new Item("Conjured", 10, 42);
        Inn inn = singleItem(conjuredItem);

        inn.updateQuality();

        assertItem(conjuredItem, "Conjured", 9, 40);
    }

    @Test
    public void with_conjured_quality_degrades_twice_but_is_never_negative() throws Exception {
        Item conjuredItem = new Item("Conjured", 10, 1);
        Inn inn = singleItem(conjuredItem);

        inn.updateQuality();

        assertItem(conjuredItem, "Conjured", 9, 0);
    }

    private void assertItem(Item item, String expectedName, int expectedSellIn, int exptectedQuality) {
        assertThat(item.getName()).isEqualTo(expectedName);
        assertThat(item.getSellIn()).isEqualTo(expectedSellIn);
        assertThat(item.getQuality()).isEqualTo(exptectedQuality);
    }

}
