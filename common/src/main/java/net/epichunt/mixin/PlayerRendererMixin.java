package net.epichunt.mixin;

import net.epichunt.client.render.entity.RavenOnShoulderLayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin {
    @Inject(method = "<init>", at = @At("TAIL"))
    private void addRavenLayer(EntityRendererProvider.Context context, boolean slim, CallbackInfo ci) {
        ((LivingEntityRendererAccessor)(Object)this).addLayer(
                new RavenOnShoulderLayer<>((PlayerRenderer)(Object)this, context.getModelSet())
        );
    }
}
