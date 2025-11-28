package com.octopusi.servermod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import software.bernie.shadowed.eliotlash.mclib.utils.MathHelper;
import net.minecraft.util.Mth;


public class RecerdoModel<T extends Entity> extends EntityModel<T> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
        new ResourceLocation("servermod", "recerdo"), "main");

    private final ModelPart body;
    private final ModelPart head;
    private final ModelPart leg0;
    private final ModelPart leg1;
    private final ModelPart leg2;
    private final ModelPart leg3;
    private final ModelPart body2;
    private final ModelPart head2;

    public RecerdoModel(ModelPart root) {
        this.body = root.getChild("body");
        this.head = root.getChild("head");
        this.leg0 = root.getChild("leg0");
        this.leg1 = root.getChild("leg1");
        this.leg2 = root.getChild("leg2");
        this.leg3 = root.getChild("leg3");
        this.body2 = root.getChild("body2");
        this.head2 = root.getChild("head2");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body",
            CubeListBuilder.create().texOffs(28, 8)
            .addBox(-5.0F, -9.0F, -7.0F, 10.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 11.0F, -2.0F, 1.5708F, 0.0F, 0.0F));

        PartDefinition head = partdefinition.addOrReplaceChild("head",
            CubeListBuilder.create().texOffs(0, 0)
            .addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(16, 16)
            .addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 12.0F, 10.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition leg0 = partdefinition.addOrReplaceChild("leg0",
            CubeListBuilder.create().texOffs(0, 16)
            .addBox(-2.0F, 0.0F, 4.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
            PartPose.offset(-3.0F, 18.0F, 3.0F));

        PartDefinition leg1 = partdefinition.addOrReplaceChild("leg1",
            CubeListBuilder.create().texOffs(0, 16).mirror()
            .addBox(-2.0F, 0.0F, 4.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
            PartPose.offset(3.0F, 18.0F, 3.0F));

        PartDefinition leg2 = partdefinition.addOrReplaceChild("leg2",
            CubeListBuilder.create().texOffs(0, 16)
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)),
            PartPose.offset(-3.0F, 18.0F, -9.0F));

        PartDefinition leg3 = partdefinition.addOrReplaceChild("leg3",
            CubeListBuilder.create().texOffs(0, 16).mirror()
            .addBox(-2.0F, 0.0F, -2.0F, 4.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)).mirror(false),
            PartPose.offset(3.0F, 18.0F, -9.0F));

        PartDefinition body2 = partdefinition.addOrReplaceChild("body2",
            CubeListBuilder.create().texOffs(28, 8)
            .addBox(-5.0F, 9.0F, -7.0F, 10.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)),
            PartPose.offsetAndRotation(0.0F, 11.0F, 20.0F, -1.5708F, 0.0F, 3.1416F));

        PartDefinition head2 = partdefinition.addOrReplaceChild("head2",
            CubeListBuilder.create().texOffs(0, 0)
            .addBox(-4.0F, -4.0F, -8.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(16, 16)
            .addBox(-2.0F, 0.0F, -9.0F, 4.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)),
            PartPose.offset(0.0F, 12.0F, -10.0F));

        return LayerDefinition.create(meshdefinition, 128, 64);
    }


    

    @Override
    public void setupAnim(T entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Animación normal de patas
        leg0.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;
        leg1.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        leg2.xRot = Mth.cos(limbSwing * 0.6662F + (float)Math.PI) * 1.4F * limbSwingAmount;
        leg3.xRot = Mth.cos(limbSwing * 0.6662F) * 1.4F * limbSwingAmount;

        // Cabeza que mira al jugador
        //head.yRot = netHeadYaw * ((float)Math.PI / 180F);
        //head.xRot = headPitch * ((float)Math.PI / 180F);

        //head2.yRot = netHeadYaw * ((float)Math.PI / 180F);
        //head2.xRot = headPitch * ((float)Math.PI / 180F);
    }






    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg0.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg1.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        leg3.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        body2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
        head2.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
    }
}
