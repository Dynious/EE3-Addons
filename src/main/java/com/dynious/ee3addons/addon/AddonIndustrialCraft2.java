package com.dynious.ee3addons.addon;

import com.dynious.ee3addons.imc.CommunicationHandler;
import com.pahimar.ee3.api.OreStack;
import com.pahimar.ee3.api.WrappedStack;
import com.pahimar.ee3.emc.EmcValue;
import ic2.api.recipe.*;
import ic2.core.AdvRecipe;
import ic2.core.AdvShapelessRecipe;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.fluids.FluidStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AddonIndustrialCraft2
{
    /**
     * EmcValues for various IC2 things
     */
    private static final EmcValue COPPER_EMC_VALUE = new EmcValue(72);
    private static final EmcValue TIN_EMC_VALUE = new EmcValue(256);
    private static final EmcValue LEAD_EMC_VALUE = new EmcValue(512);
    private static final EmcValue SILVER_EMC_VALUE = new EmcValue(1024);
    private static final EmcValue URANIUM_EMC_VALUE = new EmcValue(4096);
    private static final EmcValue SULFUR_EMC_VALUE = new EmcValue(512);
    private static final EmcValue LITHIUM_EMC_VALUE = new EmcValue(512);
    private static final EmcValue SILICON_DIOXIDE_EMC_VALUE = new EmcValue(256);
    private static final EmcValue RUBBER_WOOD_EMC_VALUE = new EmcValue(24);

    public static void sendValues()
    {
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreCopper"), COPPER_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreTin"), TIN_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreLead"), LEAD_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreSilver"), SILVER_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("oreUranium"), URANIUM_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("dustSulfur"), SULFUR_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("dustLithium"), LITHIUM_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("dustSiliconDioxide"), SILICON_DIOXIDE_EMC_VALUE);
        CommunicationHandler.sendPreValueAssignment(new OreStack("woodRubber"), RUBBER_WOOD_EMC_VALUE);
    }

    public static void sendRecipes()
    {
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.macerator
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.compressor
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.extractor
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.metalformerCutting
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.metalformerExtruding
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.metalformerRolling
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.oreWashing
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<IRecipeInput, RecipeOutput> entry : Recipes.centrifuge
                .getRecipes().entrySet())
        {
            sendRecipeEntry(entry);
        }
        for (Map.Entry<ICannerBottleRecipeManager.Input, RecipeOutput> entry : Recipes.cannerBottle
                .getRecipes().entrySet())
        {
            addCannerBottleRecipe(entry);
        }
        for (Map.Entry<ICannerEnrichRecipeManager.Input, FluidStack> entry : Recipes.cannerEnrich
                .getRecipes().entrySet())
        {
            addCannerEnrichRecipe(entry);
        }


        for (Object recipeObject : CraftingManager.getInstance().getRecipeList())
        {
            if (recipeObject instanceof AdvRecipe || recipeObject instanceof AdvShapelessRecipe)
            {
                IRecipe recipe = (IRecipe) recipeObject;
                if (recipe.getRecipeOutput() != null)
                {
                    ArrayList<WrappedStack> recipeInputs = getRecipeInputs(recipe);
                    if (recipeInputs != null && !recipeInputs.isEmpty())
                    {
                        CommunicationHandler.sendAddRecipe(new WrappedStack(recipe.getRecipeOutput()), recipeInputs);
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
                recipeOutput.setTagCompound(entry.getValue().metadata);

                WrappedStack wrappedStack;
                for (ItemStack recipeInput : entry.getKey().getInputs())
                {
                    wrappedStack = new WrappedStack(recipeInput);

                    if (wrappedStack.getWrappedStack() != null)
                    {
                        wrappedStack.setStackSize(entry.getKey().getAmount());

                        CommunicationHandler.sendAddRecipe(new WrappedStack(recipeOutput), wrappedStack);
                    }
                }
            }
        }
    }

    private static ArrayList<WrappedStack> getRecipeInputs(IRecipe recipe)
    {
        ArrayList<WrappedStack> recipeInputs = new ArrayList<WrappedStack>();

        if (recipe instanceof AdvRecipe)
        {
            AdvRecipe shapedRecipe = (AdvRecipe) recipe;

            for (int i = 0; i < shapedRecipe.input.length; i++)
            {
                if (shapedRecipe.input[i] instanceof ItemStack)
                {
                    ItemStack itemStack = ((ItemStack) shapedRecipe.input[i])
                            .copy();

                    recipeInputs.add(new WrappedStack(itemStack));
                }
                else if (shapedRecipe.input[i] instanceof String)
                {
                    OreStack stack = new OreStack((String) shapedRecipe.input[i]);

                    recipeInputs.add(new WrappedStack(stack));
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

                    recipeInputs.add(new WrappedStack(itemStack));
                }
                else if (object instanceof String)
                {
                    OreStack stack = new OreStack((String) object);

                    recipeInputs.add(new WrappedStack(stack));
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
                recipeOutput.setTagCompound(entry.getValue().metadata);

                WrappedStack wrappedStack1;
                for (ItemStack recipeInput1 : entry.getKey().container.getInputs())
                {
                    wrappedStack1 = new WrappedStack(recipeInput1);

                    if (wrappedStack1.getWrappedStack() != null)
                    {
                        wrappedStack1.setStackSize(entry.getKey().container.getAmount());

                        WrappedStack wrappedStack2;
                        for (ItemStack recipeInput2 : entry.getKey().fill.getInputs())
                        {
                            wrappedStack2 = new WrappedStack(recipeInput2);

                            if (wrappedStack2.getWrappedStack() != null)
                            {
                                wrappedStack2.setStackSize(entry.getKey().fill.getAmount());

                                CommunicationHandler.sendAddRecipe(new WrappedStack(recipeOutput), wrappedStack1, wrappedStack2);
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
            WrappedStack wrappedStack1 = new WrappedStack(entry.getKey().fluid);

            if (wrappedStack1.getWrappedStack() != null)
            {

                WrappedStack wrappedStack2;
                for (ItemStack recipeInput2 : entry.getKey().additive.getInputs())
                {
                    wrappedStack2 = new WrappedStack(recipeInput2);

                    if (wrappedStack2.getWrappedStack() != null)
                    {
                        wrappedStack2.setStackSize(entry.getKey().additive.getAmount());

                        CommunicationHandler.sendAddRecipe(new WrappedStack(entry.getValue()), wrappedStack1, wrappedStack2);
                    }
                }
            }
        }
    }
}
