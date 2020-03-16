package edu.gatech.cs2340.spacetrader.entity.inventory;


import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.spacetrader.entity.Good;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

/**
 * Test for the PlayerInventory class
 */
public class PlayerInventoryTest {

    HashMap<Good, Integer> inventory;
    PlayerInventory playerInventory;


    @Before
    public void setUp() {
        inventory = new HashMap<>();
        playerInventory = new PlayerInventory(10, new HashMap<Good, Integer>());
    } //setUp

    @Test
    public void addItemTest() {
        assertEquals(new HashMap<Good, Integer>(), playerInventory.getInv());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(0, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 3);
        inventory.put(Good.Firearms, 3);
        assertEquals(1, playerInventory.getInv().size());
        assertEquals(3, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

    } //add item

    @Test
    public void addDifferentItemsest() {
        assertEquals(new HashMap<Good, Integer>(), playerInventory.getInv());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(0, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 3);
        inventory.put(Good.Firearms, 3);

        assertEquals(1, playerInventory.getInv().size());
        assertEquals(3, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Furs, 6);
        inventory.put(Good.Furs, 6);

        assertEquals(9, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());
        assertEquals(2, playerInventory.getInv().size());
        assertEquals((Integer) 3, playerInventory.getInv().get(Good.Firearms));
        assertEquals((Integer) 6, playerInventory.getInv().get(Good.Furs));
    } //add item

    @Test (expected = IllegalArgumentException.class)
    public void addItemInventoryFullTest() {
        inventory.put(Good.Firearms, 10);
        playerInventory.getInv().put(Good.Firearms, 10);
        playerInventory.setSize(10);

        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 2);
        inventory.put(Good.Firearms, 7);

        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
    } //addItemFull

    @Test (expected = IllegalArgumentException.class)
    public void addItemUntilFullTest() {
        inventory.put(Good.Firearms, 5);
        playerInventory.getInv().put(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 2);

        assertEquals(7, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 7, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 3);
        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 1);
        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 2);
        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addFullDifferentItemTest() {

        inventory.put(Good.Firearms, 10);
        playerInventory.getInv().put(Good.Firearms, 10);
        playerInventory.setSize(10);

        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Food, 2);

        assertEquals(10, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 10, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
        assertNull(playerInventory.getInv().get(Good.Food));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addItemQuantityZero() {
        inventory.put(Good.Firearms, 5);
        playerInventory.getInv().put(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 0);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addItemQuantityNegative() {
        inventory.put(Good.Firearms, 5);
        playerInventory.getInv().put(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, -2);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
    }

    @Test (expected = IllegalArgumentException.class)
    public void addDifferentItemQuantityZero() {
        inventory.put(Good.Firearms, 5);
        playerInventory.getInv().put(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Food, 0);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
        assertNull(playerInventory.getInv().get(Good.Food));
    }

    @Test (expected = IllegalArgumentException.class)
    public void addDifferentItemQuantityNegative() {
        inventory.put(Good.Firearms, 5);
        playerInventory.getInv().put(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Food, -3);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
        assertNull(playerInventory.getInv().get(Good.Food));
    }

    @Test
    public void removeItemTest() {
        assertEquals(new HashMap<Good, Integer>(), playerInventory.getInv());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(0, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 3);
        playerInventory.addToInv(Good.Food, 5);
        playerInventory.addToInv(Good.Furs, 2);
        inventory.put(Good.Firearms, 3);
        inventory.put(Good.Food, 5);
        inventory.put(Good.Furs, 2);

        assertEquals(3, playerInventory.getInv().size());
        assertEquals(10, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.removeFromInv(Good.Firearms, 3);
        inventory.put(Good.Firearms, 0);

        assertEquals(inventory.get(Good.Firearms), playerInventory.getInv().get(Good.Firearms));
        assertEquals(7, playerInventory.getSize());
    } //add item

    @Test
    public void removeDifferentItemTest() {
        assertEquals(new HashMap<Good, Integer>(), playerInventory.getInv());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(0, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 3);
        playerInventory.addToInv(Good.Food, 5);
        playerInventory.addToInv(Good.Furs, 2);
        inventory.put(Good.Firearms, 3);
        inventory.put(Good.Food, 5);
        inventory.put(Good.Furs, 2);

        assertEquals(3, playerInventory.getInv().size());
        assertEquals(10, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.removeFromInv(Good.Firearms, 3);
        inventory.put(Good.Firearms, 0);

        assertEquals(7, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.removeFromInv(Good.Furs, 1);
        inventory.put(Good.Furs, 1);

        assertEquals(6, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());
        assertEquals(inventory.get(Good.Firearms), playerInventory.getInv().get(Good.Firearms));
        assertEquals(inventory.get(Good.Furs), playerInventory.getInv().get(Good.Furs));
    } //add item

    @Test (expected = IllegalArgumentException.class)
    public void removeItemInventoryEmptyTest() {
        assertEquals(new HashMap<Good, Integer>(), playerInventory.getInv());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(0, playerInventory.getSize());
        assertEquals(10, playerInventory.getCap());

        playerInventory.removeFromInv(Good.Firearms, 2);
    } //addItemFull

    @Test (expected = IllegalArgumentException.class)
    public void removeItemQuantityZero() {
        inventory.put(Good.Firearms, 5);
        playerInventory.addToInv(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.removeFromInv(Good.Firearms, 0);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
    }

    @Test (expected = IllegalArgumentException.class)
    public void removeItemQuantityNegative() {
        inventory.put(Good.Firearms, 5);
        playerInventory.getInv().put(Good.Firearms, 5);
        playerInventory.setSize(5);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());

        playerInventory.removeFromInv(Good.Firearms, -2);

        assertEquals(5, playerInventory.getSize());
        assertEquals(1, playerInventory.getInv().size());
        assertEquals((Integer) 5, playerInventory.getInv().get(Good.Firearms));
        assertEquals(10, playerInventory.getCap());
    }

    @Test
    public void addAllAddsAll() {
        assertEquals(0, playerInventory.getSize());
        assertEquals(0, playerInventory.getInv().size());

        HashMap<Good, Integer> toAdd = new HashMap<>();

        toAdd.put(Good.Firearms, 4);
        toAdd.put(Good.Games, 3);

        inventory.putAll(toAdd);
        Map<Good, Integer> leftOver = playerInventory.addAllToInv(toAdd);

        assertTrue(leftOver.isEmpty());
        assertEquals(inventory, playerInventory.getInv());
        assertEquals(7, playerInventory.getSize());
    }

    @Test
    public void addAllDoesNotExceedCap() {
        assertEquals(0, playerInventory.getSize());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(10, playerInventory.getCap());

        HashMap<Good, Integer> toAdd = new HashMap<>();

        toAdd.put(Good.Firearms, 4);
        toAdd.put(Good.Games, 7);

        inventory.put(Good.Firearms, 4);
        Map<Good, Integer> leftOver = playerInventory.addAllToInv(toAdd);

        assertFalse(leftOver.isEmpty());
        assertTrue(leftOver.containsKey(Good.Games));
        assertEquals((Integer) 7, leftOver.get(Good.Games));
        assertEquals(inventory, playerInventory.getInv());
        assertEquals(4, playerInventory.getSize());
    }

    @Test
    public void addAllIgnoresInvalidAdds() {
        assertEquals(0, playerInventory.getSize());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(10, playerInventory.getCap());

        HashMap<Good, Integer> toAdd = new HashMap<>();

        toAdd.put(Good.Firearms, 0);
        toAdd.put(Good.Games, -2);

        playerInventory.addAllToInv(toAdd);

        assertEquals(0, playerInventory.getSize());
    }

    @Test
    public void removeAllRemovesAll() {
        assertEquals(0, playerInventory.getSize());
        assertEquals(0, playerInventory.getInv().size());

        HashMap<Good, Integer> toAdd = new HashMap<>();

        toAdd.put(Good.Firearms, 4);
        toAdd.put(Good.Games, 3);

        inventory.putAll(toAdd);
        Map<Good, Integer> leftOver = playerInventory.addAllToInv(toAdd);

        assertTrue(leftOver.isEmpty());
        assertEquals(inventory, playerInventory.getInv());
        assertEquals(7, playerInventory.getSize());

        HashMap<Good, Integer> toRemove = new HashMap<>();

        toRemove.put(Good.Firearms, 2);
        toRemove.put(Good.Games, 1);

        inventory.put(Good.Firearms, 2);
        inventory.put(Good.Games, 2);

        leftOver = playerInventory.removeAllFromInv(toRemove);

        assertTrue(leftOver.isEmpty());
        assertEquals(inventory, playerInventory.getInv());
    }

    @Test
    public void removeAllDoesNotRemoveEmpty() {
        assertEquals(0, playerInventory.getSize());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(10, playerInventory.getCap());

        HashMap<Good, Integer> toRemove = new HashMap<>();

        toRemove.put(Good.Firearms, 4);
        toRemove.put(Good.Games, 7);

        Map<Good, Integer> leftOver = playerInventory.removeAllFromInv(toRemove);

        assertFalse(leftOver.isEmpty());
        assertTrue(leftOver.containsKey(Good.Games));
        assertEquals((Integer) 7, leftOver.get(Good.Games));
        assertEquals(inventory, playerInventory.getInv());
        assertEquals(0, playerInventory.getSize());
    }

    @Test
    public void removeAllIgnoresInvalidRemoves() {
        assertEquals(0, playerInventory.getSize());
        assertEquals(0, playerInventory.getInv().size());
        assertEquals(10, playerInventory.getCap());

        playerInventory.addToInv(Good.Firearms, 1);
        playerInventory.addToInv(Good.Games, 1);

        assertEquals(2, playerInventory.getSize());

        HashMap<Good, Integer> toRemove = new HashMap<>();

        toRemove.put(Good.Firearms, 0);
        toRemove.put(Good.Games, -2);

        playerInventory.removeAllFromInv(toRemove);

        assertEquals(2, playerInventory.getSize());
    }
} //sellTest
