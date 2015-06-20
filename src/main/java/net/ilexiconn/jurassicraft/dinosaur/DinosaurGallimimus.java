package net.ilexiconn.jurassicraft.dinosaur;

import net.ilexiconn.jurassicraft.block.BlockEncasedFossil;
import net.ilexiconn.jurassicraft.client.model.animation.AnimationGallimimus;
import net.ilexiconn.jurassicraft.entity.EntityGallimimus;
import net.ilexiconn.jurassicraft.entity.base.EntityDinosaur;
import net.ilexiconn.llibrary.client.model.entity.animation.IModelAnimator;
import net.ilexiconn.llibrary.client.model.tabula.ModelJson;
import net.minecraft.client.model.ModelBase;

public class DinosaurGallimimus extends Dinosaur
{
    private IModelAnimator animator;
    private String[] maleTextures;
    private String[] femaleTextures;
    private ModelJson model;

    public DinosaurGallimimus()
    {
        this.animator = new AnimationGallimimus();
        this.maleTextures = new String[] { "jurassicraft:textures/entities/gallimimus/gallimimus_male.png" };
        this.femaleTextures = new String[] { "jurassicraft:textures/entities/gallimimus/gallimimus_female.png" };

        try
        {
            this.model = getTabulaModel("/assets/jurassicraft/models/entities/gallimimus");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    // TODO: Figure out all the entities properties

    @Override
    public String getName()
    {
        return "Gallimimus";
    }

    @Override
    public Class<? extends EntityDinosaur> getDinosaurClass()
    {
        return EntityGallimimus.class;
    }

    @Override
    public int getPeriod()
    {
        return BlockEncasedFossil.EnumTimePeriod.CRETACEOUS.getMetadata();
    }

    @Override
    public int getEggPrimaryColor()
    {
        return 0xC57B5F;
    }

    @Override
    public int getEggSecondaryColor()
    {
        return 0x985E54;
    }

    @Override
    public double getBabyHealth()
    {
        return 16;
    }

    @Override
    public double getAdultHealth()
    {
        return 5;
    }

    @Override
    public double getBabySpeed()
    {
        return 0.62;
    }

    @Override
    public double getAttackSpeed()
    {
        return 0.50;
    }

    @Override
    public double getAdultSpeed()
    {
        return 0.50;
    }

    @Override
    public double getBabyStrength()
    {
        return 6;
    }

    @Override
    public double getAdultStrength()
    {
        return 36;
    }

    @Override
    public double getBabyLength()
    {
        return 2.0;
    }

    @Override
    public double getBabyHeight()
    {
        return 1.0;
    }

    @Override
    public double getAdultLength()
    {
        return 4.9;
    }

    @Override
    public double getAdultHeight()
    {
        return 1.7;
    }

    @Override
    public double getBabyKnockback()
    {
        return 0.3;
    }

    @Override
    public double getAdultKnockback()
    {
        return 0.6;
    }

    @Override
    public double getMinLipids()
    {
        return 500;
    }

    @Override
    public double getMinProximate()
    {
        return 1600;
    }

    @Override
    public double getMinMinerals()
    {
        return 1200;
    }

    @Override
    public double getMinVitamins()
    {
        return 1600;
    }

    @Override
    public double getRidingSpeed()
    {
        return 0;
    }

    @Override
    public float getAdultAge()
    {
        return 0.5F;
    }

    @Override
    public float getXZBoxMin()
    {
        return 0.4F;
    }

    @Override
    public float getYBoxMin()
    {
        return 0.5F;
    }

    @Override
    public float getXZBoxDelta()
    {
        return 1.2F;
    }

    @Override
    public float getYBoxDelta()
    {
        return 1.1F;
    }

    @Override
    public float getScaleAdjustment()
    {
        return 1.2F;
    }

    @Override
    public float getShadowSize()
    {
        return 0.65F;
    }

    @Override
    public ModelBase getModel()
    {
        return model;
    }

    @Override
    public String[] getMaleTextures()
    {
        return maleTextures;
    }

    @Override
    public String[] getFemaleTextures()
    {
        return femaleTextures;
    }

    @Override
    public IModelAnimator getModelAnimator()
    {
        return animator;
    }
}