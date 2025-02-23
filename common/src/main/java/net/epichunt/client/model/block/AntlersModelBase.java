package net.epichunt.client.model.block;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.Model;
import net.minecraft.client.renderer.RenderType;


@Environment(EnvType.CLIENT)
public abstract class AntlersModelBase extends Model {
    public AntlersModelBase() {
        super(RenderType::entityTranslucent);
    }

    public abstract void setupAnim(float f, float g, float h);
}
