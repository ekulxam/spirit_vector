package symbolics.division.spirit.vector.sfx;

import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.util.Identifier;

public class SimpleSFX implements SFXPack<SimpleParticleType> {
    protected final Identifier id;
    protected final SimpleParticleType PARTICLE_TYPE = FabricParticleTypes.simple();
    protected final Identifier wingsTexture;

    public SimpleSFX(Identifier id) {
        this.id = id;
        this.wingsTexture = Identifier.of(id.getNamespace(),  "textures/wing/" + id.getPath() + ".png");
    }

    @Override
    public Identifier id() {
        return id;
    }

    @Override
    public Identifier wingsTexture() {
        return wingsTexture;
    }

    @Override
    public SimpleParticleType particleType() {
        return PARTICLE_TYPE;
    }


}