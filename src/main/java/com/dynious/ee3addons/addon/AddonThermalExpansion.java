package com.dynious.ee3addons.addon;

import cofh.thermalexpansion.util.crafting.*;
import com.pahimar.ee3.api.EnergyValueRegistryProxy;
import com.pahimar.ee3.api.RecipeRegistryProxy;
import com.pahimar.ee3.exchange.OreStack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddonThermalExpansion
{
    public static void sendRecipes()
    {
        for (PulverizerManager.RecipePulverizer recipePulverizer :PulverizerManager.getRecipeList())
        {
            if (recipePulverizer.getInput() != null && recipePulverizer.getPrimaryOutput() != null)
            {
                RecipeRegistryProxy.addRecipe(recipePulverizer.getPrimaryOutput(), Arrays.asList(recipePulverizer.getInput()));
            }
        }

        for (SawmillManager.RecipeSawmill recipeSawmill : SawmillManager.getRecipeList())
        {
            if (recipeSawmill.getInput() != null && recipeSawmill.getPrimaryOutput() != null)
            {
                RecipeRegistryProxy.addRecipe(recipeSawmill.getPrimaryOutput(), Arrays.asList(recipeSawmill.getInput()));
            }
        }

        for (SmelterManager.RecipeSmelter recipeSmelter : SmelterManager.getRecipeList())
        {
            if (recipeSmelter.getPrimaryOutput() != null)
            {
                List<Object> inputs = new ArrayList<Object>();
                if (recipeSmelter.getPrimaryInput() != null)
                {
                    inputs.add(recipeSmelter.getPrimaryInput());
                }
                if (recipeSmelter.getSecondaryInput() != null)
                {
                    inputs.add(recipeSmelter.getSecondaryInput());
                }
                RecipeRegistryProxy.addRecipe(recipeSmelter.getPrimaryOutput(), inputs);
            }
        }

        for (CrucibleManager.RecipeCrucible recipeCrucible : CrucibleManager.getRecipeList())
        {
            if (recipeCrucible.getInput() != null && recipeCrucible.getOutput() != null)
            {
                RecipeRegistryProxy.addRecipe(recipeCrucible.getOutput(), Arrays.asList(recipeCrucible.getInput()));
            }
        }

        for (TransposerManager.RecipeTransposer recipeTransposer : TransposerManager.getFillRecipeList())
        {
            if (recipeTransposer.getInput() != null && recipeTransposer.getOutput() != null)
            {
                RecipeRegistryProxy.addRecipe(recipeTransposer.getOutput(), Arrays.asList(recipeTransposer.getInput(), recipeTransposer.getFluid()));
            }
        }
    }
}
