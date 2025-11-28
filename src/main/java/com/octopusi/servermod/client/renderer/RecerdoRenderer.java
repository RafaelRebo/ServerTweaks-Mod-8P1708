package com.octopusi.servermod.client.renderer;

import com.octopusi.servermod.client.model.RecerdoModel;
import com.octopusi.servermod.entity.RecerdoEntity;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;

public class RecerdoRenderer extends MobRenderer<RecerdoEntity, RecerdoModel<RecerdoEntity>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation("servermod:textures/entity/recerdo.png");

    public RecerdoRenderer(EntityRendererProvider.Context context) {
        super(context, new RecerdoModel<>(context.bakeLayer(RecerdoModel.LAYER_LOCATION)), 0.6f);
    }

    @Override
    public ResourceLocation getTextureLocation(RecerdoEntity entity) {
        return TEXTURE;
    }
}
