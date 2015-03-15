package com.dynious.ee3addons.addon;

import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import ic2.api.recipe.*;
import ic2.core.AdvRecipe;
import ic2.core.AdvShapelessRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class AddonIndustrialCraft2
{
    /**
     * EmcValues for various IC2 things
     */
    private static final int COPPER_EMC_VALUE = 72;
    private static final int TIN_EMC_VALUE = 256;
    private static final int LEAD_EMC_VALUE = 512;
    private static final int SILVER_EMC_VALUE = 1024;
    private static final int URANIUM_EMC_VALUE = 4096;
    private static final int SULFUR_EMC_VALUE = 512;
    private static final int LITHIUM_EMC_VALUE = 512;
    private static final int SILICON_DIOXIDE_EMC_VALUE = 256;
    private static final int RUBBER_WOOD_EMC_VALUE = 24;

    public static void sendValues()
    {
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreCopper"), COPPER_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreTin"), TIN_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreLead"), LEAD_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreSilver"), SILVER_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("oreUranium"), URANIUM_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustSulfur"), SULFUR_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustLithium"), LITHIUM_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("dustSiliconDioxide"), SILICON_DIOXIDE_EMC_VALUE);
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(new OreStack("woodRubber"), RUBBER_WOOD_EMC_VALUE);
    }

    public static void sendRecipes()
    {
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.macerator.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.compressor.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.extractor.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.metalformerCutting.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.metalformerExtruding.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.metalformerRolling.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.oreWashing.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.centrifuge.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.blockcutter.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.blastfurance.getRecipes().entrySet())
            sendRecipeEntry(entry);
        for (Map.Entry<ICannerBottleRecipeManager.Input, RecipeOutput> entry : Recipes.cannerBottle.getRecipes().entrySet())
            addCannerBottleRecipe(entry);
        for (Map.Entry<ICannerEnrichRecipeManager.Input, FluidStack> entry : Recipes.cannerEnrich.getRecipes().entrySet())
            addCannerEnrichRecipe(entry);


        for (Object recipeObject : CraftingManager.getInstance().getRecipeList())
        {
            if (recipeObject instanceof AdvRecipe || recipeObject instanceof AdvShapelessRecipe)
            {
                IRecipe recipe = (IRecipe) recipeObject;
                if (recipe.getRecipeOutput() != null)
                {
                    List<Object> recipeInputs = getRecipeInputs(recipe);
                    if (recipeInputs != null && !recipeInputs.isEmpty())
                    {
                        RecipeRegistryProxy.addRecipe(recipe.getRecipeOutput(), recipeInputs);
                    }
                }
            }
        }
    }

    private static void sendRecipeEntry(Map.Entry<IRecipeInput, RecipeOutput> entry)
    {
        List<ItemStack> recipeStackOutputs = entry.getValue().items;
        if (recipeStackOutputs.size() == 1)
        {
            ItemStack recipeOutput = recipeStackOutputs.get(0);
            if (recipeOutput != null)
            {
                recipeOutput = recipeOutput.copy();
                recipeOutput.setTagCompound(entry.getValue().metadata);

                for (ItemStack recipeInput : entry.getKey().getInputs())
                {
                    if (recipeInput != null)
                    {
                        recipeInput = recipeInput.copy();
                        recipeInput.stackSize = entry.getKey().getAmount();

                        RecipeRegistryProxy.addRecipe(recipeOutput, Arrays.asList(recipeInput));
                    }
                }
            }
        }
    }

    private static List<Object> getRecipeInputs(IRecipe recipe)
    {
        List<Object> recipeInputs = new ArrayList<Object>();

        if (recipe instanceof AdvRecipe)
        {
            AdvRecipe shapedRecipe = (AdvRecipe) recipe;

            for (int i = 0; i < shapedRecipe.input.length; i++)
            {
                if (shapedRecipe.input[i] instanceof ItemStack)
                {
                    ItemStack itemStack = ((ItemStack) shapedRecipe.input[i]).copy();
                    recipeInputs.add(itemStack);
                }
                else if (shapedRecipe.input[i] instanceof String)
                {
                    OreStack stack = new OreStack((String) shapedRecipe.input[i]);
                    recipeInputs.add(stack);
                }
            }
        }
        else if (recipe instanceof AdvShapelessRecipe)
        {
            AdvShapelessRecipe shapelessRecipe = (AdvShapelessRecipe) recipe;

            for (Object object : shapelessRecipe.input)
            {
                if (object instanceof ItemStack)
                {
                    ItemStack itemStack = ((ItemStack) object).copy();
                    recipeInputs.add(itemStack);
                }
                else if (object instanceof String)
                {
                    OreStack stack = new OreStack((String) object);
                    recipeInputs.add(stack);
                }
            }
        }

        return recipeInputs;
    }

    private static void addCannerBottleRecipe(Map.Entry<ICannerBottleRecipeManager.Input, RecipeOutput> entry)
    {
        List<ItemStack> recipeStackOutputs = entry.getValue().items;
        if (recipeStackOutputs.size() == 1)
        {
            ItemStack recipeOutput = recipeStackOutputs.get(0);
            if (recipeOutput != null)
            {
                recipeOutput = recipeOutput.copy();
                recipeOutput.setTagCompound(entry.getValue().metadata);

                for (ItemStack recipeInput1 : entry.getKey().container.getInputs())
                {
                    if (recipeInput1 != null)
                    {
                        recipeInput1 = recipeInput1.copy();
                        recipeInput1.stackSize = entry.getKey().container.getAmount();

                        for (ItemStack recipeInput2 : entry.getKey().fill.getInputs())
                        {
                            if (recipeInput2 != null)
                            {
                                recipeInput2 = recipeInput2.copy();
                                recipeInput2.stackSize = entry.getKey().fill.getAmount();
                                RecipeRegistryProxy.addRecipe(recipeOutput, Arrays.asList(recipeInput1, recipeInput2));
                            }
                        }
                    }
                }
            }
        }
    }

    private static void addCannerEnrichRecipe(Map.Entry<ICannerEnrichRecipeManager.Input, FluidStack> entry)
    {
        if (entry.getValue() != null)
        {
            if (entry.getKey().fluid != null)
            {
                for (ItemStack recipeInput2 : entry.getKey().additive.getInputs())
                {
                    if (recipeInput2 != null)
                    {
                        recipeInput2 = recipeInput2.copy();
                        recipeInput2.stackSize = entry.getKey().additive.getAmount();
                        RecipeRegistryProxy.addRecipe(entry.getValue(), Arrays.asList(entry.getKey().fluid, recipeInput2));
                    }
                }
            }
        }
    }
}
