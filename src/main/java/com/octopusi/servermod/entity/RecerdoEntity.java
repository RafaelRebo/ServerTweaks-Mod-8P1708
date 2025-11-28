package com.octopusi.servermod.entity;

import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;


import javax.annotation.Nullable;

import com.octopusi.servermod.ServerMod;
import com.octopusi.servermod.ModSounds;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class RecerdoEntity extends Animal {

    public RecerdoEntity(EntityType<? extends Animal> type, Level world) {
        super(type, world);
        // NO llames a setHealth aquí
    }

    @Override
    public boolean isBaby() {
        return false;
    }

    @Override
    protected void dropCustomDeathLoot(DamageSource source, int looting, boolean recentlyHit) {
        for (int i = 0; i < 10; i++) {
            this.spawnAtLocation(new ItemStack(Items.PORKCHOP));
        }
        super.dropCustomDeathLoot(source, looting, recentlyHit);
    }

    @Override
    @Nullable
    public RecerdoEntity getBreedOffspring(ServerLevel world, AgeableMob mate) {
        return null;
    }

    @Override
    protected void registerGoals() {
        super.registerGoals();
        // Movimiento aleatorio
        this.goalSelector.addGoal(1, new RandomStrollGoal(this, 1.0D));
        // Mirar al jugador cercano
        this.goalSelector.addGoal(2, new LookAtPlayerGoal(this, Player.class, 6.0F));
        // Saltar si se siente atacado
        this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.RECERDO_AMBIENT.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.RECERDO_HURT.get();
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.RECERDO_DEATH.get();
    }




    // ---- atributos por defecto para este mob ----
    public static AttributeSupplier.Builder createAttributes() {
        return net.minecraft.world.entity.Mob.createMobAttributes()
                .add(Attributes.MAX_HEALTH, 10.0D)     // vida máxima
                .add(Attributes.MOVEMENT_SPEED, 0.25D) // velocidad
                .add(Attributes.ATTACK_DAMAGE, 6.0D)   // daño (si ataca)
                .add(Attributes.FOLLOW_RANGE, 16.0D);  // rango de seguimiento
    }
}
