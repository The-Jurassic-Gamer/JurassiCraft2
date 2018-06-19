package org.jurassicraft.client.model;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.*;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraftforge.registries.IForgeRegistryEntry;
import org.apache.commons.lang3.tuple.Pair;
import org.jurassicraft.client.event.DinosaurModelHandler;
import org.jurassicraft.server.api.StackNBTProvider;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;
import java.util.List;
import java.util.Map;

public class DinosaurItemModel<T extends IForgeRegistryEntry.Impl<T> & Comparable<T>> implements IBakedModel {

    private final Map<T, Map<Object, IBakedModel>> bakedModelMap;
    private IBakedModel model;
    private TextureAtlasSprite missing;

    public DinosaurItemModel(StackNBTProvider<T> provider, TextureMap map) {
        this.bakedModelMap = provider.produceMap(map);
        this.missing = map.getMissingSprite();
    }

    public boolean shouldRegister(){
        return !this.bakedModelMap.isEmpty();
    }

    @Override
    public List<BakedQuad> getQuads(@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
        return model.getQuads(state, side, rand);//Not really needed
    }

    @Override
    public Pair<? extends IBakedModel, Matrix4f> handlePerspective(ItemCameraTransforms.TransformType cameraTransformType) {
        return model.handlePerspective(cameraTransformType);
    }

    @Override
    public boolean isAmbientOcclusion() {
        return true;
    }

    @Override
    public boolean isGui3d() {
        return true;
    }

    @Override
    public TextureAtlasSprite getParticleTexture() {
        return missing;
    }

    @Override
    public boolean isBuiltInRenderer() {
        return false;
    }

    @Override
    public ItemOverrideList getOverrides() {
        return new ItemOverrideList(Lists.newArrayList()) {
            @Override
            public IBakedModel handleItemState(IBakedModel originalModel, ItemStack stack, @Nullable World world, @Nullable EntityLivingBase entity) {
                StackNBTProvider provider = StackNBTProvider.getFromStack(stack);
                model = bakedModelMap.getOrDefault(provider != null ? provider.getValue(stack) : null, Maps.newHashMap())
                        .getOrDefault(provider.getVarient(stack), DinosaurModelHandler.MISSING_MODEL);

                return super.handleItemState(originalModel, stack, world, entity);
            }
        };
    }
}