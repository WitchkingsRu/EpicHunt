package net.epichunt.entity;

import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.ai.goal.Goal.Flag;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class FishBreedGoal extends Goal {
    private static final TargetingConditions PARTNER_TARGETING = TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight();
    protected final AbstractBreedableFish fish;
    private final Class<? extends AbstractBreedableFish> partnerClass;
    protected final Level level;
    @Nullable
    protected AbstractBreedableFish partner;
    private int loveTime;
    private final double speedModifier;

    public FishBreedGoal(AbstractBreedableFish fish, double d) {
        this(fish, d, fish.getClass());
    }

    public FishBreedGoal(AbstractBreedableFish fish, double d, Class<? extends AbstractBreedableFish> class_) {
        this.fish = fish;
        this.level = fish.level();
        this.partnerClass = class_;
        this.speedModifier = d;
        this.setFlags(EnumSet.of(Flag.MOVE, Flag.LOOK));
    }

    public boolean canUse() {
        if (!this.fish.isInLove()) {
            return false;
        } else {
            this.partner = this.getFreePartner();
            return this.partner != null;
        }
    }

    public boolean canContinueToUse() {
        return this.partner.isAlive() && this.partner.isInLove() && this.loveTime < 60;
    }

    public void stop() {
        this.partner = null;
        this.loveTime = 0;
    }

    public void tick() {
        this.fish.getLookControl().setLookAt(this.partner, 10.0F, (float)this.fish.getMaxHeadXRot());
        this.fish.getNavigation().moveTo(this.partner, this.speedModifier);
        ++this.loveTime;
        if (this.loveTime >= this.adjustedTickDelay(60) && this.fish.distanceToSqr(this.partner) < 9.0) {
            this.breed();
        }

    }

    @Nullable
    private AbstractBreedableFish getFreePartner() {
        List<? extends AbstractBreedableFish> list = this.level.getNearbyEntities(this.partnerClass, PARTNER_TARGETING, this.fish, this.fish.getBoundingBox().inflate(8.0));
        double d = Double.MAX_VALUE;
        AbstractBreedableFish fish = null;
        Iterator var5 = list.iterator();

        while(var5.hasNext()) {
            AbstractBreedableFish fish2 = (AbstractBreedableFish)var5.next();
            if (this.fish.canMate(fish2) && this.fish.distanceToSqr(fish2) < d) {
                fish = fish2;
                d = this.fish.distanceToSqr(fish2);
            }
        }

        return fish;
    }

    protected void breed() {
        this.fish.spawnChildFromBreeding((ServerLevel)this.level, this.partner);
    }
}