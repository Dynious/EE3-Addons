package com.dynious.ee3addons.addon;

import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;

public class AddonOres
{
    private static final int COPPER_EMC_VALUE = 72;
    private static final int TIN_EMC_VALUE = 256;
    private static final int LEAD_EMC_VALUE = 512;
    private static final int SILVER_EMC_VALUE = 1024;
    private static final int NICKEL_EMC_VALUE = 2048;
    private static final int URANIUM_EMC_VALUE = 4096;
    private static final int SULFUR_EMC_VALUE = 512;
    private static final int LITHIUM_EMC_VALUE = 512;
    private static final int SILICON_DIOXIDE_EMC_VALUE = 256;
    private static final int RUBBER_WOOD_EMC_VALUE = 24;

    public static void sendValues()
    {
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreCopper"), COPPER_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotCopper"), COPPER_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreTin"), TIN_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotTin"), TIN_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreLead"), LEAD_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotLead"), LEAD_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreSilver"), SILVER_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotSilver"), SILVER_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreNickel"), NICKEL_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("ingotNickel"), NICKEL_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreUranium"), URANIUM_EMC_VALUE);

        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustSulfur"), SULFUR_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustLithium"), LITHIUM_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustSiliconDioxide"), SILICON_DIOXIDE_EMC_VALUE);

        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("itemRubber"), RUBBER_WOOD_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("woodRubber"), RUBBER_WOOD_EMC_VALUE);
    }
}
