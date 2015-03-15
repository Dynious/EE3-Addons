package com.dynious.ee3addons;

import com.dynious.ee3addons.addon.AddonBuildCraft;
import com.dynious.ee3addons.addon.AddonIndustrialCraft2;
import com.dynious.ee3addons.addon.AddonThermalExpansion;
import com.dynious.ee3addons.lib.Reference;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Reference.MOD_ID, name = Reference.MOD_NAME, dependencies = Reference.DEPENDENCIES)
public class EE3Addons
{
    @Mod.Instance(Reference.MOD_ID)
    public static EE3Addons instance;

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void preInit(FMLPreInitializationEvent event)
    {
        if (Loader.isModLoaded("IC2"))
        {
            AddonIndustrialCraft2.sendValues();
        }
        if (Loader.isModLoaded("BuildCraft|Core"))
        {
            AddonBuildCraft.sendValues();
        }
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void init(FMLInitializationEvent event)
    {
        if (Loader.isModLoaded("IC2"))
        {
            AddonIndustrialCraft2.sendRecipes();
        }
        if (Loader.isModLoaded("BuildCraft|Core"))
        {
            AddonBuildCraft.sendRecipes();
        }
    }

    @Mod.EventHandler
    @SuppressWarnings("unused")
    public void postInit(FMLPostInitializationEvent event)
    {
        if (Loader.isModLoaded("ThermalExpansion"))
        {
            AddonThermalExpansion.sendValues();
            AddonThermalExpansion.sendRecipes();
        }
    }
}
