package net.epichunt.mixin;

import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(LivingEntityRenderer.class)
public abstract class LivingEntityRendererAccessor {
    @Shadow
    protected abstract <T extends LivingEntity, M extends EntityModel<T>> boolean addLayer(RenderLayer<T, M> layer);
}
