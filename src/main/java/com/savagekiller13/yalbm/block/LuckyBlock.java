package com.savagekiller13.yalbm.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LuckyBlock extends Block {


    public Integer[] itemRarity = {1,2,3,4,5,6,7};

    public Map<Item, Integer> itemDrops = new HashMap<>();

    public LuckyBlock(Settings settings) {
        super(settings);


        itemDrops.put(Items.APPLE, itemRarity[0]);
        itemDrops.put(Items.DIAMOND, itemRarity[2]);
        itemDrops.put(Items.GOLD_INGOT, itemRarity[1]);
        itemDrops.put(Items.IRON_INGOT, itemRarity[1]);
        itemDrops.put(Items.EMERALD, itemRarity[3]);
        itemDrops.put(Items.SPONGE, itemRarity[3]);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        ItemStack stack = luckyDrop(itemDrops);

        if (!world.isClient()) {
            dropStack(world, pos, stack);
        }

        super.onBreak(world, pos, state, player);
    }

    public ItemStack luckyDrop(Map<Item, Integer> list) {
        int listLength = list.size();

        Random rand = new Random();

        int item = rand.nextInt(listLength);
        int chance;

        chance = rand.nextInt(randomChance((Integer) list.values().toArray()[item]));

        if (chance == 0) chance = 1;

        int[] itemAmountList = {64, 48, 32, 16, 8, 4, 2};
        int itemAmount = 0;
        int itemAmountNeeded;

        

        Item neededItem = (Item) list.keySet().toArray()[item];


        return new ItemStack(neededItem, itemAmount);

    }

    public int randomChance(Integer rarity) {

        int maxChance = rarity;

        Random rand = new Random();

        if (maxChance == 1) {
            return 1;
        } else {
            int chance = rand.nextInt(maxChance);

            if (chance == 0) chance = 1;

            return chance;
        }

    }
}



























