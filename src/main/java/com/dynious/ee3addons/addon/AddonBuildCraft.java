package com.dynious.ee3addons.addon;

import buildcraft.BuildCraftEnergy;
import buildcraft.api.recipes.BuildcraftRecipeRegistry;
import buildcraft.api.recipes.IFlexibleRecipe;
import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;

import java.util.ArrayList;
import java.util.List;

public class AddonBuildCraft
{
    /**
     * EmcValues for various BC things
     */
    private static final int OIL_EMC_VALUE = 1275;

    public static void sendValues()
    {
        EnergyValueRegistryProxy.addPreAssignedEnergyValue(BuildCraftEnergy.fluidOil, OIL_EMC_VALUE);
    }

    /*
    @SuppressWarnings("unchecked")
    public static void sendRecipes()
    {
        for (IFlexibleRecipe<ItemStack> assemblyRecipe : BuildcraftRecipeRegistry.assemblyTable.getRecipes())
        {
            if (assemblyRecipe.output != null)
            {
                List<Object> inputs = new ArrayList<Object>();

                Object[] recipeInputs = assemblyRecipe.input;
                for (int i = 0; i < recipeInputs.length; i++)
                {
                    Object input = recipeInputs[i];
                    if (input != null)
                    {
                        if (input instanceof ItemStack)
                        {
                            inputs.add(input);
                        }
                        else if (input instanceof ArrayList)
                        {
                            ArrayList<ItemStack> ores = (ArrayList<ItemStack>) input;
                            for (ItemStack ore : ores)
                            {
                                int[] oreID = OreDictionary.getOreIDs(ore);
                                if (oreID.length > 0)
                                {
                                    inputs.add((Integer)recipeInputs[i + 1], new OreStack(ore));
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!inputs.isEmpty())
                {
                    RecipeRegistryProxy.addRecipe(assemblyRecipe.output, inputs);
                }
            }
        }

        for (RefineryRecipes.Recipe recipe : RefineryRecipes.getRecipes())
        {
            FluidStack output = recipe.result;
            if (output != null)
            {
                List<Object> inputs = new ArrayList<Object>();
                if (recipe.ingredient1 != null)
                {
                    inputs.add(recipe.ingredient1);
                }
                if (recipe.ingredient2 != null)
                {
                    inputs.add(recipe.ingredient2);
                }
                if (!inputs.isEmpty())
                {
                    RecipeRegistryProxy.addRecipe(output, inputs);
                }
            }
        }
    }
    */
}
