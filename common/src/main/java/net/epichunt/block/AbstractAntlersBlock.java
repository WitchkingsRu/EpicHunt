package net.epichunt.block;

import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;

public class AbstractAntlersBlock extends SkullBlock {
    public AbstractAntlersBlock(Type type, Properties properties) {
        super(type, properties);
    }

    public static enum Types implements Type {
        ROE_DEER;

        private Types() {
        }
    }
}
