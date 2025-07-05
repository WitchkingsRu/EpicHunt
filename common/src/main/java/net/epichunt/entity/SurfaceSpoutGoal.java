package net.epichunt.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.animal.WaterAnimal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;
import java.util.Random;

public class SurfaceSpoutGoal extends Goal {
    private final WaterAnimal mob;
    private final Level level;
    private final int interval;
    private int cooldown;
    public boolean shouldSpawnParticles = false;

    public SurfaceSpoutGoal(WaterAnimal mob, int interval) {
        this.mob = mob;
        this.level = mob.level();
        this.interval = interval;
        this.cooldown = mob.getRandom().nextInt(interval);
    }

    @Override
    public boolean canUse() {
        return true; // постоянная цель, но сама управляет частотой
    }

    @Override
    public void tick() {
        cooldown--;
        if (cooldown > 0) return;
        cooldown = interval;

        BlockPos headPos = BlockPos.containing(mob.getX(), mob.getEyeY(), mob.getZ());

        if (isAtWaterSurface(headPos)) {
            shouldSpawnParticles = true;
        } else {
            BlockPos target = findSurfaceAbove(mob.blockPosition(), 16);
            if (target != null) {
                mob.getNavigation().moveTo(target.getX(), target.getY(), target.getZ(), 1.0);
            }
        }
    }

    private boolean isAtWaterSurface(BlockPos headPos) {
        return level.getFluidState(headPos).is(FluidTags.WATER)
                && level.getBlockState(headPos.above()).isAir();
    }

    private BlockPos findSurfaceAbove(BlockPos start, int maxDistance) {
        BlockPos.MutableBlockPos pos = new BlockPos.MutableBlockPos(start.getX(), start.getY(), start.getZ());
        for (int dy = 0; dy < maxDistance; dy++) {
            pos.move(0, 1, 0);
            if (level.getBlockState(pos).isAir() && level.getFluidState(pos.below()).is(FluidTags.WATER)) {
                return pos.below(); // Вода под воздухом — значит, поверхность
            }
        }
        return null;
    }

    private void spawnSpoutParticles() {

            Vec3 base = mob.position().add(0, mob.getBbHeight(), 0); // вершина хитбокса
            for (int i = 0; i < 10; i++) {
                double x = base.x + mob.getRandom().nextGaussian() * 0.15;
                double y = base.y + i * 0.25;
                double z = base.z + mob.getRandom().nextGaussian() * 0.15;

                level.addAlwaysVisibleParticle(ParticleTypes.HEART,
                        x, y, z,
                        0, 0.1 + mob.getRandom().nextDouble() * 0.1, 0);
            }
            System.out.println(">>> Particles out on " + mob.tickCount);
        }

}

