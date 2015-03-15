package com.dynious.ee3addons.addon;

import com.dynious.ee3addons.imc.CommunicationHandler;
import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcValue;
import thermalexpansion.util.crafting.*;

import java.util.ArrayList;
import java.util.List;

public class AddonThermalExpansion
{
    /**
     * EmcValues for various TE things
     */
    private static final EmcValue COPPER_EMC_VALUE = new EmcValue(72);
    private static final EmcValue TIN_EMC_VALUE = new EmcValue(256);
    private static final EmcValue LEAD_EMC_VALUE = new EmcValue(512);
    private static final EmcValue SILVER_EMC_VALUE = new EmcValue(1024);
    private static final EmcValue NICKEL_EMC_VALUE = new EmcValue(2048);

    public static void sendValues()
    {
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreCopper"), COPPER_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreTin"), TIN_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreLead"), LEAD_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreSilver"), SILVER_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreNickel"), NICKEL_EMC_VALUE);
    }

    public static void sendRecipes()
    {
        for (PulverizerManager.RecipePulverizer recipePulverizer :PulverizerManager.getRecipeList())
        {
            if (recipePulverizer.getInput() != null && recipePulverizer.getPrimaryOutput() != null)
            {
                CommunicationHandler.sendAddRecipe(new WrappedStack(recipePulverizer.getPrimaryOutput()), recipePulverizer.getInput());
            }
        }

        for (SawmillManager.RecipeSawmill recipeSawmill : SawmillManager.getRecipeList())
        {
            if (recipeSawmill.getInput() != null && recipeSawmill.getPrimaryOutput() != null)
            {
                CommunicationHandler.sendAddRecipe(new WrappedStack(recipeSawmill.getPrimaryOutput()), recipeSawmill.getInput());
            }
        }

        for (SmelterManager.RecipeSmelter recipeSmelter : SmelterManager.getRecipeList())
        {
            if (recipeSmelter.getPrimaryOutput() != null)
            {
                List<WrappedStack> inputs = new ArrayList<WrappedStack>();
                if (recipeSmelter.getPrimaryInput() != null)
                {
                    inputs.add(new WrappedStack(recipeSmelter.getPrimaryInput()));
                }
                if (recipeSmelter.getSecondaryInput() != null)
                {
                    inputs.add(new WrappedStack(recipeSmelter.getSecondaryInput()));
                }
                CommunicationHandler.sendAddRecipe(new WrappedStack(recipeSmelter.getPrimaryOutput()), inputs);
            }
        }

        for (CrucibleManager.RecipeCrucible recipeCrucible : CrucibleManager.getRecipeList())
        {
            if (recipeCrucible.getInput() != null && recipeCrucible.getOutput() != null)
            {
                CommunicationHandler.sendAddRecipe(new WrappedStack(recipeCrucible.getOutput()), recipeCrucible.getInput());
            }
        }

        for (TransposerManager.RecipeTransposer recipeTransposer : TransposerManager.getFillRecipeList())
        {
            if (recipeTransposer.getInput() != null && recipeTransposer.getOutput() != null)
            {
                CommunicationHandler.sendAddRecipe(new WrappedStack(recipeTransposer.getOutput()), recipeTransposer.getInput());
            }
        }

        for (TransposerManager.RecipeTransposer recipeTransposer : TransposerManager.getExtractionRecipeList())
        {
            if (recipeTransposer.getInput() != null && recipeTransposer.getOutput() != null)
            {
                CommunicationHandler.sendAddRecipe(new WrappedStack(recipeTransposer.getOutput()), recipeTransposer.getInput());
            }
        }
    }
}
