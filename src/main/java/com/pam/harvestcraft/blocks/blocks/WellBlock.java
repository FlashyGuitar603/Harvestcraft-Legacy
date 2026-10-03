package com.pam.harvestcraft.blocks.blocks;

import java.util.List;

import javax.annotation.Nullable;

import com.pam.harvestcraft.HarvestCraft;
import com.pam.harvestcraft.item.ItemRegistry;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
//import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Items;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
//import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
//import net.minecraft.util.IStringSerializable;

public class WellBlock extends Block {

	//public static final PropertyEnum<EnumBlockWell> type = PropertyEnum.create("type", EnumBlockWell.class);

	public static final String registryName = "well";
	/*
	public enum EnumBlockWell implements IStringSerializable
	{

		sink_0(0, "sink_0"),
		sink_1(1, "sink_1"),
		sink_2(2, "sink_2");

		public final int meta;
		public final String name;

		EnumBlockWell(int meta, String name)
		{
			this.meta = meta;
			this.name = name;
		}

		public int getMeta()
		{
			return this.meta;
		}

		public final static EnumBlockWell[] values = values();

		public static EnumBlockWell byMetadata(int meta)
		{
			if (meta < 1)
			{
				return values[meta];
			}

			return sink_2;
		}

		@Override
		public String getName()
		{
			return this.name;
		}
	}*/

	public WellBlock() {
		super(Material.ROCK);
		setCreativeTab(HarvestCraft.modTab);
		setSoundType(SoundType.STONE);
		//this.setDefaultState(this.blockState.getBaseState().withProperty(type, EnumBlockWell.sink_0));
		setHardness(1.0f);
	}
	/*
	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos, EntityPlayer player)
	{
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
	}

	@Override
	public IBlockState getStateFromMeta(int meta)
	{
		return this.getDefaultState().withProperty(type, EnumBlockWell.byMetadata(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state)
	{
		return ((EnumBlockWell) state.getValue(type)).getMeta();
	}*/
	
	 @SideOnly(Side.CLIENT)
	    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn)
	    {
	        super.addInformation(stack, worldIn, tooltip, flagIn);
	        tooltip.add(TextFormatting.GOLD + I18n.format("tooltip.well_block"));
	    }

	@Override
	public boolean onBlockActivated(World world, BlockPos pos, IBlockState state, EntityPlayer playerIn, EnumHand hand,
			EnumFacing side, float hitX, float hitY, float hitZ) {
		ItemStack heldItem = playerIn.getHeldItem(hand);

		if(heldItem.isEmpty()) {
			return true;
		}
		else {

			Item item = heldItem.getItem();

			if(item == Items.BUCKET) {

				heldItem.shrink(1);

				if(heldItem.isEmpty()) {
					playerIn.setHeldItem(hand, new ItemStack(Items.WATER_BUCKET));
				}
				else if(!playerIn.inventory.addItemStackToInventory(new ItemStack(Items.WATER_BUCKET))) {
					playerIn.dropItem(new ItemStack(Items.WATER_BUCKET), false);
				}

				return true;
			}
			else if(item == Items.GLASS_BOTTLE) {

                        ItemStack itemstack3 = PotionUtils.addPotionToItemStack(new ItemStack(Items.POTIONITEM), PotionTypes.WATER);
                        heldItem.shrink(1);

                        if (itemstack3.isEmpty())
                        {
                            playerIn.setHeldItem(hand, itemstack3);
                        }
                        else if (!playerIn.inventory.addItemStackToInventory(itemstack3))
                        {
                            playerIn.dropItem(itemstack3, false);
                        }
                        else if (playerIn instanceof EntityPlayerMP)
                        {
                            ((EntityPlayerMP)playerIn).sendContainerToPlayer(playerIn.inventoryContainer);
                        }


                return true;
            }
			else if(item == ItemRegistry.freshwaterItem) {
				{
					playerIn.inventory.addItemStackToInventory(new ItemStack(ItemRegistry.freshwaterItem));
				}
				return true;
			}

			return false;
		}
	}

}
