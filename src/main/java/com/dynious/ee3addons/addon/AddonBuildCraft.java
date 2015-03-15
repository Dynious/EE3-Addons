package com.dynious.ee3addons.addon;

import buildcraft.BuildCraftEnergy;
import buildcraft.api.recipes.AssemblyRecipe;
import buildcraft.api.recipes.RefineryRecipes;
import com.dynious.ee3addons.imc.CommunicationHandler;
import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcValue;
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
    private static final EmcValue OIL_EMC_VALUE = new EmcValue(1275);

    public static void sendValues()
    {
        CommunicationHandler.sendPreValueAssignment(BuildCraftEnergy.fluidOil, OIL_EMC_VALUE);
    }

    @SuppressWarnings("unchecked")
    public static void sendRecipes()
    {
        for (AssemblyRecipe assemblyRecipe : AssemblyRecipe.assemblyRecipes)
        {
            if (assemblyRecipe.output != null)
            {
                List<WrappedStack> inputs = new ArrayList<WrappedStack>();

                Object[] recipeInputs = assemblyRecipe.input;
                for (int i = 0; i < recipeInputs.length; i++)
                {
                    Object input = recipeInputs[i];
                    if (input != null)
                    {
                        if (input instanceof ItemStack)
                        {
                            inputs.add(new WrappedStack(input));
                        }
                        else if (input instanceof ArrayList)
                        {
                            ArrayList<ItemStack> ores = (ArrayList<ItemStack>) input;
                            for (ItemStack ore : ores)
                            {
                                int oreID = OreDictionary.getOreID(ore);
                                if (oreID != -1)
                                {
                                    inputs.add(new WrappedStack(new OreStack(OreDictionary.getOreName(oreID), (Integer)recipeInputs[i + 1])));
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!inputs.isEmpty())
                {
                    CommunicationHandler.sendAddRecipe(new WrappedStack(assemblyRecipe.output), inputs);
                }
            }
        }

        for (RefineryRecipes.Recipe recipe : RefineryRecipes.getRecipes())
        {
            FluidStack output = recipe.result;
            if (output != null)
            {
                List<WrappedStack> inputs = new ArrayList<WrappedStack>();
                if (recipe.ingredient1 != null)
                {
                    inputs.add(new WrappedStack(recipe.ingredient1));
                }
                if (recipe.ingredient2 != null)
                {
                    inputs.add(new WrappedStack(recipe.ingredient2));
                }
                if (!inputs.isEmpty())
                {
                    CommunicationHandler.sendAddRecipe(new WrappedStack(output), inputs);
                }
            }
        }
    }
}
