package net.epichunt.entity;

import net.minecraft.world.entity.Mob;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

public class SurfaceSwimGoal extends Goal {
    private final Mob mob;

    public SurfaceSwimGoal(Mob mob) {
        this.mob = mob;
    }

    @Override
    public boolean canUse() {
        // Цель активируется, если моб в воде
        return mob.isInWater() && mob.getFluidHeight(FluidTags.WATER) > mob.getFluidJumpThreshold();
    }

    @Override
    public boolean requiresUpdateEveryTick() {
        return true; // Обновляем каждый тик
    }

    @Override
    public void tick() {
        double surfaceY = mob.getFluidHeight(FluidTags.WATER) + mob.getBlockY();
        // Если моб ниже поверхности воды, плавно поднимаем его
        if (mob.getY() < surfaceY) {
            Vec3 currentMovement = mob.getDeltaMovement();
            double verticalSpeed = Math.min(0.1, surfaceY - mob.getY()); // Плавный подъём
            // Устанавливаем только вертикальную скорость, оставляя горизонтальную неизменной
            mob.setDeltaMovement(currentMovement.x, verticalSpeed, currentMovement.z);
        }
    }
}