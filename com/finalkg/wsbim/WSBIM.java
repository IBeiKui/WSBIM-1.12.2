package com.finalkg.wsbim;

import java.io.IOException;

import org.apache.logging.log4j.Logger;

import com.finalkg.wsbim.client.lib.event.ItemTooltipHandler;
import com.finalkg.wsbim.common.block.BlockIceMaker;
import com.finalkg.wsbim.common.block.BlockLeather;
import com.finalkg.wsbim.common.block.BlockLeatherColored;
import com.finalkg.wsbim.common.block.BlockMixedMetal;
import com.finalkg.wsbim.common.block.BlockMixedMetalChest;
import com.finalkg.wsbim.common.block.BlockMixedMetalOre;
import com.finalkg.wsbim.common.block.BlockMud;
import com.finalkg.wsbim.common.block.BlockObsidianChest;
import com.finalkg.wsbim.common.block.BlockWetSand;
import com.finalkg.wsbim.common.item.ItemBackpack;
import com.finalkg.wsbim.common.item.ItemChestItem;
import com.finalkg.wsbim.common.item.ItemModdedArmor;
import com.finalkg.wsbim.common.item.ItemModdedAxe;
import com.finalkg.wsbim.common.item.ItemModdedHoe;
import com.finalkg.wsbim.common.item.ItemModdedPickaxe;
import com.finalkg.wsbim.common.item.ItemModdedShovel;
import com.finalkg.wsbim.common.item.ItemModdedSword;
import com.finalkg.wsbim.common.lib.ColorHelper;
import com.finalkg.wsbim.common.lib.EnumChestItem;
import com.finalkg.wsbim.common.net.PacketDispatcher;
import com.finalkg.wsbim.common.proxy.CommonProxy;
import com.finalkg.wsbim.common.sound.SoundsHandler;
import com.finalkg.wsbim.common.tile.TileEntityIceMaker;
import com.finalkg.wsbim.common.tile.TileEntityMixedMetalChest;
import com.finalkg.wsbim.common.tile.TileEntityObsidianChest;
import com.finalkg.wsbim.common.world.gen.WorldGeneratorMixedMetalOre;
import com.finalkg.wsbim.common.world.gen.WorldGeneratorMudPit;

import net.minecraft.block.Block;
import net.minecraft.block.BlockColored;
import net.minecraft.block.material.Material;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.SoundEvents;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemCloth;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.util.EnumHelper;
import net.minecraftforge.event.RegistryEvent.Register;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.NetworkRegistry;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.fml.relauncher.Side;

@Mod(modid = WSBIM.MODID, name = WSBIM.NAME, version = WSBIM.VERSION)
public class WSBIM
{
	
	//------------ESSENTIALS BEGIN----------------//
    public static final String MODID = "wsbim";
    public static final String NAME = "What Should Be In Minecraft";
    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
    public static final int MASTER_BUILD_NUMBER = 1;
    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
    public static final int REVISION_NUMBER = 0;
    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
    public static final int BUILD_NUMBER = 0;
    /**(a for alpha, b for beta, e for experimental, r for release)*/
    public static final char BUILD_TYPE = 'r';
    /**FORMAT: MCVERSION-MASTER_BUILD_NUMBER.REVISION_NUMBER.BUILD_NUMBER-(a for alpha, b for beta, e for experimental, r for release)*/
    public static final String VERSION = "1.12.2-"+MASTER_BUILD_NUMBER+"."+REVISION_NUMBER+"."+BUILD_NUMBER+"-"+BUILD_TYPE;
   
    @Instance(MODID)
    public static WSBIM instance;
  
    public static Logger logger;
    
    /**OPTIONS SYSTEM INSTANCE*/
    public static WSBIMOptions options = new WSBIMOptions();
    
    /**WSBIM UPDATE CHECKER INSTANCE*/
    public static WSBIMUpdateChecker updateChecker = new WSBIMUpdateChecker();
    
    /**PROXIES*/
	@SidedProxy(clientSide = "com.finalkg.wsbim.client.proxy.ClientProxy", serverSide = "com.finalkg.wsbim.common.proxy.CommonProxy")
	public static CommonProxy proxy;
    //------------ESSENTIALS END----------------//
    
    //------------ITEMS BEGIN------------//
    
    //General Items
    public static Item itemMixedMetalIngot;
	public static Item itemIronStick;
	public static Item itemObsidianPickaxe;
	public static Item itemObsidianShovel;
	public static Item itemObsidianHoe;
	public static Item itemObsidianAxe;
	public static Item itemObsidianSword;
	public static Item itemStonePlate;
	public static Item itemCobblestonePlate;
	public static Item itemDiamondPlate;
	public static Item itemWoodenPlate;
	public static Item itemMixedMetalContents;
	public static Item itemObsidianPlate;
	public static Item itemGoldenStick;
	public static Item itemSack;
	public static Item itemWallet;
	public static Item itemSmallBackpack;
	public static Item itemMediumBackpack;
	public static Item itemLargeBackpack;
	public static Item itemExtraLargeBackpack;
	
	public static Item itemEmeraldSword;
	public static Item itemEmeraldPickaxe;
	public static Item itemEmeraldShovel;
	public static Item itemEmeraldAxe;
	public static Item itemEmeraldHoe;
	public static Item itemIronPlate;
	
	public static Item itemMixedMetalSword;
	public static Item itemMixedMetalPickaxe;
	public static Item itemMixedMetalShovel;
	public static Item itemMixedMetalAxe;
	public static Item itemMixedMetalHoe;
	
	public static Item itemObsidianHelmet;
	public static Item itemObsidianChestplate;
	public static Item itemObsidianLeggings;
	public static Item itemObsidianBoots;
	
	public static Item itemCobblestoneHelmet;
	public static Item itemCobblestoneChestplate;
	public static Item itemCobblestoneLeggings;
	public static Item itemCobblestoneBoots;
	
	public static Item itemStoneHelmet;
	public static Item itemStoneChestplate;
	public static Item itemStoneLeggings;
	public static Item itemStoneBoots;
	
	public static Item itemWoodenHelmet;
	public static Item itemWoodenChestplate;
	public static Item itemWoodenLeggings;
	public static Item itemWoodenBoots;
	
	public static Item itemMixedMetalHelmet;
	public static Item itemMixedMetalChestplate;
	public static Item itemMixedMetalLeggings;
	public static Item itemMixedMetalBoots;
	
	public static Item itemEmeraldHelmet;
	public static Item itemEmeraldChestplate;
	public static Item itemEmeraldLeggings;
	public static Item itemEmeraldBoots;

    //ITEM BLOCKS
    public static ItemBlock itemBlockMixedMetalOre;
    public static ItemBlock itemBlockMixedMetal;
    public static ItemBlock itemBlockWetSand;
    public static ItemBlock itemBlockMud;
    public static ItemBlock itemBlockLeatherBlock;
    public static ItemBlock itemBlockLeatherBlockColored;
    public static ItemBlock itemBlockIceMaker;
    public static ItemBlock itemBlockObsidianChest;
    public static ItemBlock itemBlockMixedMetalChest;
    //------------ITEMS END------------//
    
    //------------BLOCKS BEGIN------------//
    public static Block blockMixedMetalOre;
    public static Block blockMixedMetal;
	public static Block blockMixedMetalBlock;
	public static Block blockWetSand;
	public static Block blockMud;
	public static Block blockLeatherBlock;
	public static BlockColored blockLeatherBlockColored;
	public static Block blockIceMaker;
	public static Block blockIceMakerActive;
	public static Block blockObsidianChest;
	public static Block blockMixedMetalChest;
    //------------BLOCKS END------------//
	
	//------------TOOL MATERIALS BEGIN------------//
	public static final Item.ToolMaterial obsidianToolMaterial = EnumHelper.addToolMaterial("obsidian", 3, 4700, 5.5F, 2.5F, 16);
	public static final Item.ToolMaterial mixedMetalToolMaterial = EnumHelper.addToolMaterial("mixedMetal", 3, 3000, 15F, 4.5F, 22);
	public static final Item.ToolMaterial emeraldToolMaterial = EnumHelper.addToolMaterial("emerald", 2, 600, 10F, 3F, 20);
	//------------TOOL MATERIALS END------------//
	
	//------------ARMOR MATERIALS START-------------//
	public static final ItemArmor.ArmorMaterial obsidianArmorMaterial = EnumHelper.addArmorMaterial(MODID+":"+"obsidian", MODID+":"+"obsidian", 260, new int[]{4, 6, 6, 4}, 12, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 1.75F);
	public static final ItemArmor.ArmorMaterial cobblestoneArmorMaterial = EnumHelper.addArmorMaterial(MODID+":"+"cobblestone", MODID+":"+"cobblestone", 10, new int[]{1,3,4,2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F);
	public static final ItemArmor.ArmorMaterial stoneArmorMaterial = EnumHelper.addArmorMaterial(MODID+":"+"stone", MODID+":"+"stone", 10, new int[]{1,3,4,2}, 14, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F);
	public static final ItemArmor.ArmorMaterial woodenArmorMaterial = EnumHelper.addArmorMaterial(MODID+":"+"wooden", MODID+":"+"wooden", 8, new int[] {1,2,3,2}, 15, SoundEvents.ITEM_ARMOR_EQUIP_GENERIC, 0F);
	public static final ItemArmor.ArmorMaterial mixedMetalArmorMaterial = EnumHelper.addArmorMaterial(MODID+":"+"mixedmetal", MODID+":"+"mixedmetal", 120, new int[]{4, 7, 9, 4}, 6, SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND, 3F);
	public static final ItemArmor.ArmorMaterial emeraldArmorMaterial = EnumHelper.addArmorMaterial(MODID+":"+"emerald", MODID+":"+"emerald", 25, new int[]{3,4,6,4}, 9, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 0F);
	//------------ARMOR MATERIALS END------------//
	 
    //------------CREATIVE TABS BEGIN------------//
    public static CreativeTabs ourTab = new CreativeTabs("whatshouldbeinminecrafttab"){

		public ItemStack getTabIconItem() {
			return (new ItemStack(itemMixedMetalContents));
		}
		
	};
    
    //------------CREATIVE TABS END-------------//
    @EventHandler
    public void preInit(FMLPreInitializationEvent e)
    {
        logger = e.getModLog();
        try {
			options.createOptions();
		} catch (IOException e1) {
			e1.printStackTrace();
			logger.warn("UNABLE TO CREATE OPTIONS FILE FOR WSBIM");
		}
        PacketDispatcher.registerPackets();
    }

    @EventHandler
    public void init(FMLInitializationEvent e)
    {
        if(e.getSide() == Side.SERVER && WSBIM.options.checkForUpdatesOnServer){
			updateChecker.checkUpdateForServer();
		}
        proxy.registerProxies();
        NetworkRegistry.INSTANCE.registerGuiHandler(MODID, new WSBIMGuiHandler());
    	registerSmeltingRecipes();
    	registerWorldGen();
    	registerTileEntities();
    	SoundsHandler.registerSounds();
    }
    
    @EventHandler
    public void postInit(FMLPostInitializationEvent e) 
    {
    	
    }
    
    /**
     * Registers smelting recipes
     */
    private void registerSmeltingRecipes() 
    {
    	GameRegistry.addSmelting(new ItemStack(this.blockMixedMetalOre), new ItemStack(this.itemMixedMetalIngot), 1000.0F);
    	GameRegistry.addSmelting(new ItemStack(this.blockWetSand), new ItemStack(Blocks.SAND), 0);
    	GameRegistry.addSmelting(new ItemStack(this.itemMixedMetalContents), new ItemStack(this.itemMixedMetalIngot), 500.0F);
    }

    /**
     * Registers world generators
     */
    private void registerWorldGen() 
    {
    	GameRegistry.registerWorldGenerator(new WorldGeneratorMudPit(), 0);
    	GameRegistry.registerWorldGenerator(new WorldGeneratorMixedMetalOre(), 1);
    	//DEPRECATED GameRegistry.registerWorldGenerator(new WorldGeneratorWetSand(), 2);
    }
    /**
     * Registers tile entities
     */
    private void registerTileEntities() 
    {
    	GameRegistry.registerTileEntity(TileEntityIceMaker.class, blockIceMaker.getRegistryName());
    	GameRegistry.registerTileEntity(TileEntityObsidianChest.class, blockObsidianChest.getRegistryName());
    	GameRegistry.registerTileEntity(TileEntityMixedMetalChest.class, blockMixedMetalChest.getRegistryName());
    }
    /**
     * Responsible for all items and blocks being registered
     * @author ARlO-DESKTOP
     *
     */
    @EventBusSubscriber(modid = MODID)
    public static class RegistrationHandler{
    	 	@SubscribeEvent
    	    /**
    	     * Registers all items for WSBIM
    	     */
    	    public static void registerItems(Register<Item> e) 
    	    {
    	    	//Item object creation
    	    	itemMixedMetalIngot = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "mixedmetalingot").setUnlocalizedName("mixedMetalIngot");
    	    	itemIronStick = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "ironStick").setUnlocalizedName("ironStick");
    	    	itemGoldenStick = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "goldenStick").setUnlocalizedName("goldenStick");
    	    	itemWoodenPlate = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "woodenPlate").setUnlocalizedName("woodenPlate");
    	    	itemCobblestonePlate = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "cobblestonePlate").setUnlocalizedName("cobblestonePlate");
    	    	itemStonePlate = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "stonePlate").setUnlocalizedName("stonePlate");
    	    	itemIronPlate = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "ironPlate").setUnlocalizedName("ironPlate");
    	    	itemDiamondPlate = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "diamondPlate").setUnlocalizedName("diamondPlate");
    	    	itemObsidianPlate = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "obsidianPlate").setUnlocalizedName("obsidianPlate");
    	    	itemMixedMetalContents = new Item().setCreativeTab(ourTab).setRegistryName(MODID, "mixedMetalContents").setUnlocalizedName("mixedMetalContents");
    	    	itemSack = new ItemChestItem(EnumChestItem.SACK).setCreativeTab(ourTab).setRegistryName(MODID, "sack").setUnlocalizedName("sack");
    	    	itemWallet = new ItemChestItem(EnumChestItem.WALLET).setCreativeTab(ourTab).setRegistryName(MODID, "wallet").setUnlocalizedName("wallet");
    	    	itemSmallBackpack = new ItemBackpack(EnumChestItem.SMALL_BACKPACK).setCreativeTab(ourTab).setRegistryName(MODID, "smallbackpack").setUnlocalizedName("smallbackpack");
    	    	itemMediumBackpack = new ItemBackpack(EnumChestItem.MEDIUM_BACKPACK).setCreativeTab(ourTab).setRegistryName(MODID, "mediumbackpack").setUnlocalizedName("mediumbackpack");
    	    	itemLargeBackpack = new ItemBackpack(EnumChestItem.LARGE_BACKPACK).setCreativeTab(ourTab).setRegistryName(MODID, "largebackpack").setUnlocalizedName("largebackpack");
    	    	itemExtraLargeBackpack = new ItemBackpack(EnumChestItem.EXTRA_LARGE_BACKPACK, ColorHelper.WHITE).setCreativeTab(ourTab).setRegistryName(MODID, "extralargebackpack").setUnlocalizedName("extralargebackpack");
    	    	//ItemArmor creation
    	    	itemWoodenHelmet = new ItemModdedArmor(woodenArmorMaterial, EntityEquipmentSlot.HEAD).setCreativeTab(ourTab).setRegistryName(MODID, "itemWoodenHelmet").setUnlocalizedName("itemWoodenHelmet");
    	    	itemWoodenChestplate = new ItemModdedArmor(woodenArmorMaterial, EntityEquipmentSlot.CHEST).setCreativeTab(ourTab).setRegistryName(MODID, "itemWoodenChestplate").setUnlocalizedName("itemWoodenChestplate");
    	    	itemWoodenLeggings = new ItemModdedArmor(woodenArmorMaterial, EntityEquipmentSlot.LEGS).setCreativeTab(ourTab).setRegistryName(MODID, "itemWoodenLeggings").setUnlocalizedName("itemWoodenLeggings");
    	    	itemWoodenBoots = new ItemModdedArmor(woodenArmorMaterial, EntityEquipmentSlot.FEET).setCreativeTab(ourTab).setRegistryName(MODID, "itemWoodenBoots").setUnlocalizedName("itemWoodenBoots");
    	    	itemCobblestoneHelmet = new ItemModdedArmor(cobblestoneArmorMaterial, EntityEquipmentSlot.HEAD).setCreativeTab(ourTab).setRegistryName(MODID, "itemCobblestoneHelmet").setUnlocalizedName("itemCobblestoneHelmet");
    	    	itemCobblestoneChestplate = new ItemModdedArmor(cobblestoneArmorMaterial, EntityEquipmentSlot.CHEST).setCreativeTab(ourTab).setRegistryName(MODID, "itemCobblestoneChestplate").setUnlocalizedName("itemCobblestoneChestplate");
    	    	itemCobblestoneLeggings = new ItemModdedArmor(cobblestoneArmorMaterial, EntityEquipmentSlot.LEGS).setCreativeTab(ourTab).setRegistryName(MODID, "itemCobblestoneLeggings").setUnlocalizedName("itemCobblestoneLeggings");
    	    	itemCobblestoneBoots = new ItemModdedArmor(cobblestoneArmorMaterial, EntityEquipmentSlot.FEET).setCreativeTab(ourTab).setRegistryName(MODID, "itemCobblestoneBoots").setUnlocalizedName("itemCobblestoneBoots");
    	    	itemStoneHelmet = new ItemModdedArmor(stoneArmorMaterial, EntityEquipmentSlot.HEAD).setCreativeTab(ourTab).setRegistryName(MODID, "itemStoneHelmet").setUnlocalizedName("itemStoneHelmet");
    	    	itemStoneChestplate = new ItemModdedArmor(stoneArmorMaterial, EntityEquipmentSlot.CHEST).setCreativeTab(ourTab).setRegistryName(MODID, "itemStoneChestplate").setUnlocalizedName("itemStoneChestplate");
    	    	itemStoneLeggings = new ItemModdedArmor(stoneArmorMaterial, EntityEquipmentSlot.LEGS).setCreativeTab(ourTab).setRegistryName(MODID, "itemStoneLeggings").setUnlocalizedName("itemStoneLeggings");
    	    	itemStoneBoots = new ItemModdedArmor(stoneArmorMaterial, EntityEquipmentSlot.FEET).setCreativeTab(ourTab).setRegistryName(MODID, "itemStoneBoots").setUnlocalizedName("itemStoneBoots");
    	    	itemEmeraldHelmet = new ItemModdedArmor(emeraldArmorMaterial, EntityEquipmentSlot.HEAD).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldHelmet").setUnlocalizedName("itemEmeraldHelmet");
    	    	itemEmeraldChestplate = new ItemModdedArmor(emeraldArmorMaterial, EntityEquipmentSlot.CHEST).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldChestplate").setUnlocalizedName("itemEmeraldChestplate");
    	    	itemEmeraldLeggings = new ItemModdedArmor(emeraldArmorMaterial, EntityEquipmentSlot.LEGS).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldLeggings").setUnlocalizedName("itemEmeraldLeggings");
    	    	itemEmeraldBoots = new ItemModdedArmor(emeraldArmorMaterial, EntityEquipmentSlot.FEET).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldBoots").setUnlocalizedName("itemEmeraldBoots");
    	    	itemObsidianHelmet = new ItemModdedArmor(obsidianArmorMaterial, EntityEquipmentSlot.HEAD).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianHelmet").setUnlocalizedName("itemObsidianHelmet");
    	    	itemObsidianChestplate = new ItemModdedArmor(obsidianArmorMaterial, EntityEquipmentSlot.CHEST).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianChestplate").setUnlocalizedName("itemObsidianChestplate");
    	    	itemObsidianLeggings = new ItemModdedArmor(obsidianArmorMaterial, EntityEquipmentSlot.LEGS).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianLeggings").setUnlocalizedName("itemObsidianLeggings");
    	    	itemObsidianBoots = new ItemModdedArmor(obsidianArmorMaterial, EntityEquipmentSlot.FEET).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianBoots").setUnlocalizedName("itemObsidianBoots");
    	    	itemMixedMetalHelmet = new ItemModdedArmor(mixedMetalArmorMaterial, EntityEquipmentSlot.HEAD).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalHelmet").setUnlocalizedName("itemMixedMetalHelmet");
    	    	itemMixedMetalChestplate = new ItemModdedArmor(mixedMetalArmorMaterial, EntityEquipmentSlot.CHEST).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalChestplate").setUnlocalizedName("itemMixedMetalChestplate");
    	    	itemMixedMetalLeggings = new ItemModdedArmor(mixedMetalArmorMaterial, EntityEquipmentSlot.LEGS).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalLeggings").setUnlocalizedName("itemMixedMetalLeggings");
    	    	itemMixedMetalBoots = new ItemModdedArmor(mixedMetalArmorMaterial, EntityEquipmentSlot.FEET).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalBoots").setUnlocalizedName("itemMixedMetalBoots");
    	    	
    	    	//ItemTool creation
    	    	itemObsidianPickaxe = new ItemModdedPickaxe(obsidianToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianPickaxe").setUnlocalizedName("itemObsidianPickaxe");
    	    	itemObsidianAxe = new ItemModdedAxe(obsidianToolMaterial, 6F).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianAxe").setUnlocalizedName("itemObsidianAxe");
    	    	itemObsidianShovel = new ItemModdedShovel(obsidianToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianShovel").setUnlocalizedName("itemObsidianShovel");
    	    	itemObsidianHoe = new ItemModdedHoe(obsidianToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianHoe").setUnlocalizedName("itemObsidianHoe");
    	    	itemObsidianSword = new ItemModdedSword(obsidianToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemObsidianSword").setUnlocalizedName("itemObsidianSword");
    	    	itemEmeraldPickaxe = new ItemModdedPickaxe(emeraldToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldPickaxe").setUnlocalizedName("itemEmeraldPickaxe");
    	    	itemEmeraldAxe = new ItemModdedAxe(emeraldToolMaterial, 6F).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldAxe").setUnlocalizedName("itemEmeraldAxe");
    	    	itemEmeraldShovel = new ItemModdedShovel(emeraldToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldShovel").setUnlocalizedName("itemEmeraldShovel");
    	    	itemEmeraldHoe = new ItemModdedHoe(emeraldToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldHoe").setUnlocalizedName("itemEmeraldHoe");
    	    	itemEmeraldSword = new ItemModdedSword(emeraldToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemEmeraldSword").setUnlocalizedName("itemEmeraldSword");
    	    	itemMixedMetalPickaxe = new ItemModdedPickaxe(mixedMetalToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalPickaxe").setUnlocalizedName("itemMixedMetalPickaxe");
    	    	itemMixedMetalAxe = new ItemModdedAxe(mixedMetalToolMaterial, 6.5F).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalAxe").setUnlocalizedName("itemMixedMetalAxe");
    	    	itemMixedMetalShovel = new ItemModdedShovel(mixedMetalToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalShovel").setUnlocalizedName("itemMixedMetalShovel");
    	    	itemMixedMetalHoe = new ItemModdedHoe(mixedMetalToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalHoe").setUnlocalizedName("itemMixedMetalHoe");
    	    	itemMixedMetalSword = new ItemModdedSword(mixedMetalToolMaterial).setCreativeTab(ourTab).setRegistryName(MODID, "itemMixedMetalSword").setUnlocalizedName("itemMixedMetalSword");
    	    	
    	    	//ItemBlock creation
    	    	itemBlockMixedMetalOre = new ItemBlock(blockMixedMetalOre);
    	    	itemBlockMixedMetalOre.setRegistryName(blockMixedMetalOre.getRegistryName());
    	    	itemBlockMixedMetal = new ItemBlock(blockMixedMetal);
    	    	itemBlockMixedMetal.setRegistryName(blockMixedMetal.getRegistryName());
    	    	itemBlockWetSand = new ItemBlock(blockWetSand);
    	    	itemBlockWetSand.setRegistryName(blockWetSand.getRegistryName());
    	    	itemBlockMud = new ItemBlock(blockMud);
    	    	itemBlockMud.setRegistryName(blockMud.getRegistryName());
    	    	itemBlockLeatherBlock = new ItemBlock(blockLeatherBlock);
    	    	itemBlockLeatherBlock.setRegistryName(blockLeatherBlock.getRegistryName());
    	    	itemBlockLeatherBlockColored = new ItemCloth(blockLeatherBlockColored);
    	    	itemBlockLeatherBlockColored.setRegistryName(blockLeatherBlockColored.getRegistryName());
    	    	itemBlockIceMaker = new ItemBlock(blockIceMaker);
    	    	itemBlockIceMaker.setRegistryName(blockIceMaker.getRegistryName());
    	    	itemBlockObsidianChest = new ItemBlock(blockObsidianChest);
    	    	itemBlockObsidianChest.setRegistryName(blockObsidianChest.getRegistryName());
    	    	itemBlockMixedMetalChest = new ItemBlock(blockMixedMetalChest);
    	    	itemBlockMixedMetalChest.setRegistryName(blockMixedMetalChest.getRegistryName());
    	    	//Item registration
    	    	e.getRegistry().register(itemMixedMetalIngot);
    	    	e.getRegistry().register(itemIronStick);
    	    	e.getRegistry().register(itemGoldenStick);
    	    	e.getRegistry().register(itemWoodenPlate);
    	    	e.getRegistry().register(itemStonePlate);
    	    	e.getRegistry().register(itemIronPlate);
    	    	e.getRegistry().register(itemCobblestonePlate);
    	    	e.getRegistry().register(itemDiamondPlate);
    	    	e.getRegistry().register(itemObsidianPlate);
    	    	e.getRegistry().register(itemMixedMetalContents);
    	    	e.getRegistry().register(itemSack);
    	    	e.getRegistry().register(itemWallet);
    	    	e.getRegistry().register(itemSmallBackpack);
    	    	e.getRegistry().register(itemMediumBackpack);
    	    	e.getRegistry().register(itemLargeBackpack);
    	    	e.getRegistry().register(itemExtraLargeBackpack);
    	    	e.getRegistry().register(itemWoodenHelmet);
    	    	e.getRegistry().register(itemWoodenChestplate);
    	    	e.getRegistry().register(itemWoodenLeggings);
    	    	e.getRegistry().register(itemWoodenBoots);
    	    	e.getRegistry().register(itemCobblestoneHelmet);
    	    	e.getRegistry().register(itemCobblestoneChestplate);
    	    	e.getRegistry().register(itemCobblestoneLeggings);
    	    	e.getRegistry().register(itemCobblestoneBoots);
    	    	e.getRegistry().register(itemStoneHelmet);
    	    	e.getRegistry().register(itemStoneChestplate);
    	    	e.getRegistry().register(itemStoneLeggings);
    	    	e.getRegistry().register(itemStoneBoots);
    	    	e.getRegistry().register(itemEmeraldHelmet);
    	    	e.getRegistry().register(itemEmeraldChestplate);
    	    	e.getRegistry().register(itemEmeraldLeggings);
    	    	e.getRegistry().register(itemEmeraldBoots);
    	    	e.getRegistry().register(itemObsidianHelmet);
    	    	e.getRegistry().register(itemObsidianChestplate);
    	    	e.getRegistry().register(itemObsidianLeggings);
    	    	e.getRegistry().register(itemObsidianBoots);
    	    	e.getRegistry().register(itemMixedMetalHelmet);
    	    	e.getRegistry().register(itemMixedMetalChestplate);
    	    	e.getRegistry().register(itemMixedMetalLeggings);
    	    	e.getRegistry().register(itemMixedMetalBoots);
    	    	e.getRegistry().register(itemObsidianPickaxe);
    	    	e.getRegistry().register(itemObsidianAxe);
    	    	e.getRegistry().register(itemObsidianShovel);
    	    	e.getRegistry().register(itemObsidianHoe);
    	    	e.getRegistry().register(itemObsidianSword);
    	    	e.getRegistry().register(itemEmeraldPickaxe);
    	    	e.getRegistry().register(itemEmeraldAxe);
    	    	e.getRegistry().register(itemEmeraldShovel);
    	    	e.getRegistry().register(itemEmeraldHoe);
    	    	e.getRegistry().register(itemEmeraldSword);
    	    	e.getRegistry().register(itemMixedMetalPickaxe);
    	    	e.getRegistry().register(itemMixedMetalAxe);
    	    	e.getRegistry().register(itemMixedMetalShovel);
    	    	e.getRegistry().register(itemMixedMetalHoe);
    	    	e.getRegistry().register(itemMixedMetalSword);
    	    	//ItemBlock registration
    	    	e.getRegistry().register(itemBlockMixedMetalOre);
    	    	e.getRegistry().register(itemBlockMixedMetal);
    	    	e.getRegistry().register(itemBlockWetSand);
    	    	e.getRegistry().register(itemBlockMud);
    	    	e.getRegistry().register(itemBlockLeatherBlock);
    	    	e.getRegistry().register(itemBlockLeatherBlockColored);
    	    	e.getRegistry().register(itemBlockIceMaker);
    	    	e.getRegistry().register(itemBlockObsidianChest);
    	    	e.getRegistry().register(itemBlockMixedMetalChest);
    	    }
    	 	/**
    	     * Registers blocks for WSBIM
    	     */
    	    @SubscribeEvent
    	    public static void registerBlocks(Register<Block> e)
    	    {
    	    	//Block creation
    	    	blockMixedMetalOre = new BlockMixedMetalOre(Material.ROCK).setCreativeTab(ourTab).setUnlocalizedName("mixedMetalOre").setRegistryName("mixedMetalOre").setHardness(4F).setResistance(20F);
    	    	blockMixedMetal = new BlockMixedMetal(Material.IRON).setCreativeTab(ourTab).setUnlocalizedName("mixedMetalBlock").setRegistryName("mixedMetalBlock").setHardness(5F).setResistance(100F);
    	    	blockWetSand = new BlockWetSand().setCreativeTab(ourTab).setUnlocalizedName("wetSand").setRegistryName("wetSand").setHardness(0.5F).setResistance(2.5F);
    	    	blockMud = new BlockMud().setCreativeTab(ourTab).setUnlocalizedName("mud").setRegistryName("mud").setHardness(0.5F).setResistance(4F);
    	    	blockLeatherBlock = new BlockLeather().setCreativeTab(ourTab).setUnlocalizedName("leatherBlock").setRegistryName("leatherBlock").setHardness(0.8F).setResistance(4F);
    	    	blockLeatherBlockColored = (BlockColored) new BlockLeatherColored().setCreativeTab(ourTab).setUnlocalizedName("leatherBlockColored").setRegistryName("leatherBlockColored").setHardness(0.8F).setResistance(4F);
    			//Tile Entities
    	    	blockIceMaker = new BlockIceMaker(false).setCreativeTab(ourTab).setUnlocalizedName("blockIceMaker").setRegistryName("blockIceMaker");
    	    	blockIceMakerActive = new BlockIceMaker(true).setCreativeTab(ourTab).setUnlocalizedName("blockIceMakerActive").setRegistryName("blockIceMakerActive");
    	    	blockObsidianChest = new BlockObsidianChest().setCreativeTab(ourTab).setUnlocalizedName("obsidianChest").setRegistryName("obsidianChest");
    	    	blockMixedMetalChest = new BlockMixedMetalChest().setCreativeTab(ourTab).setUnlocalizedName("mixedMetalChest").setRegistryName("mixedMetalChest");
    	    	//Block registration
    	    	e.getRegistry().register(blockMixedMetalOre);
    	    	e.getRegistry().register(blockMixedMetal);
    	    	e.getRegistry().register(blockWetSand);
    	    	e.getRegistry().register(blockMud);
    	    	e.getRegistry().register(blockLeatherBlock);
    	    	e.getRegistry().register(blockLeatherBlockColored);
    	    	e.getRegistry().register(blockIceMaker);
    	    	e.getRegistry().register(blockIceMakerActive);
    	    	e.getRegistry().register(blockObsidianChest);
    	    	e.getRegistry().register(blockMixedMetalChest); 
    	    }
    }
   
}
