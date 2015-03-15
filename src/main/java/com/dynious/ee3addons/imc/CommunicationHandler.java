package com.dynious.ee3addons.imc;

import com.dynious.ee3addons.lib.Reference;
import com.pahimar.ee3.api.RecipeMapping;
import com.pahimar.ee3.api.StackValueMapping;
import com.pahimar.ee3.emc.EmcValue;
import com.pahimar.ee3.imc.InterModCommsOperations;
import cpw.mods.fml.common.event.FMLInterModComms;

import java.util.Arrays;
import java.util.List;

public class CommunicationHandler
{
    public static void sendAddRecipe(Object outputObject, List<?> inputObjects)
    {
        FMLInterModComms.sendMessage(Reference.EE3_MOD_ID, InterModCommsOperations.RECIPE_ADD, new RecipeMapping(outputObject, inputObjects).toJson());
    }

    public static void sendAddRecipe(Object outputObject, Object... inputObjects)
    {
        List<?> inputObjectsList = Arrays.asList(inputObjects);
        FMLInterModComms.sendMessage(Reference.EE3_MOD_ID, InterModCommsOperations.RECIPE_ADD, new RecipeMapping(outputObject, inputObjectsList).toJson());
    }

    public static void sendPreValueAssignment(Object object, EmcValue emcValue)
    {
        sendPreValueAssignment(new StackValueMapping(object, emcValue));
    }

    public static void sendPreValueAssignment(StackValueMapping stackValueMapping)
    {
        if (stackValueMapping != null)
        {
            FMLInterModComms.sendMessage(Reference.EE3_MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_PRE, stackValueMapping.toJson());
        }
    }

    public static void sendPostValueAssignment(Object object, EmcValue emcValue)
    {
        sendPostValueAssignment(new StackValueMapping(object, emcValue));
    }

    public static void sendPostValueAssignment(StackValueMapping stackValueMapping)
    {
        if (stackValueMapping != null)
        {
            FMLInterModComms.sendMessage(Reference.EE3_MOD_ID, InterModCommsOperations.EMC_ASSIGN_VALUE_POST, stackValueMapping.toJson());
        }
    }
}
