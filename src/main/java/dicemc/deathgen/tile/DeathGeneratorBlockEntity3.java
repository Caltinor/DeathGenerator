package dicemc.deathgen.tile;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import dicemc.deathgen.block.DeathGeneratorBlock3;
import dicemc.deathgen.setup.Config;
import dicemc.deathgen.setup.Registration;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.energy.CapabilityEnergy;
import net.minecraftforge.energy.EnergyStorage;

public class DeathGeneratorBlockEntity3 extends BlockEntity implements IGeneratorBE{
	private final int tier = 3;
    private final EnergyStorage energyStorage = new EnergyStorage((int) Math.pow(Config.GENERATOR_CAPACITY.get(), tier), (int) Math.pow(Config.GENERATOR_TRANSFER_RATE.get(), tier));
    
	public DeathGeneratorBlockEntity3(BlockPos pos, BlockState state) {
		super(Registration.DEATH_GENERATOR_TYPE3.get(), pos, state);
	}
	
	@Override
    public void load(CompoundTag tag) {
		energyStorage.deserializeNBT(tag.get("energy"));
		super.load(tag);
	}
	
	@Override
    public CompoundTag save(CompoundTag tag) {
		tag.put("energy", energyStorage.serializeNBT());
		return super.save(tag);
	}
	
	public void serverTick(Level world, BlockPos pos) {
		if (world.isClientSide) return;
		AABB bounds = AABB.of(new BoundingBox(pos).inflate(Config.DAMAGE_RANGE.get()));
		List<LivingEntity> affectedEntities = world.getEntitiesOfClass(LivingEntity.class, bounds);
      	double dmgRatio = Config.DAMAGE_RATIO.get();
      	for (LivingEntity entity : affectedEntities) {
      		if (!Config.KILL_ANIMALS.get() && Registration.ANIMALS.contains(entity.getType())) continue;
      		if (!Config.KILL_MONSTERS.get() && Registration.MONSTERS.contains(entity.getType())) continue;
      		if (!Config.KILL_ILLAGERS.get() && Registration.ILLAGERS.contains(entity.getType())) continue;
      		if (!Config.KILL_VILLAGERS.get() && Registration.VILLAGERS.contains(entity.getType())) continue;
      		if (!Config.KILL_PLAYERS.get() && entity.getType().equals(EntityType.PLAYER)) continue;
    	  	float damageDealt = entity.getMaxHealth() * (float)dmgRatio * tier;
    	  	entity.hurt(DamageSource.MAGIC, damageDealt);
    	  	energyStorage.receiveEnergy((int) (damageDealt * Config.ENERGY_PER_HITPOINT.get()), false);
      	}
      	sendOutPower();
	}
	
	public int getEnergyAmount() {
		return energyStorage.getEnergyStored();
	}
	
	public int getTier() {return tier;}
	
	private void sendOutPower() {
        AtomicInteger capacity = new AtomicInteger(energyStorage.getEnergyStored());
        if (capacity.get() > 0) {
        	Direction direction = level.getBlockState(worldPosition).getValue(DeathGeneratorBlock3.FACING).getOpposite();
            BlockEntity te = level.getBlockEntity(worldPosition.relative(direction));
            if (te != null) {
                boolean doContinue = te.getCapability(CapabilityEnergy.ENERGY, direction).map(handler -> {
                            if (handler.canReceive()) {
                                int received = handler.receiveEnergy(Math.min(capacity.get(), 1000), false);
                                capacity.addAndGet(-received);
                                energyStorage.extractEnergy(received, false);
                                setChanged();
                                return capacity.get() > 0;
                            } else {
                                return true;
                            }
                        }
                ).orElse(true);
                if (!doContinue) {
                    return;
                }
            }
        }
    }
}
