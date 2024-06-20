package symbolics.division.spirit.vector;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.LivingEntityFeatureRendererRegistrationCallback;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import symbolics.division.spirit.vector.render.SpiritWingsFeatureRenderer;
import symbolics.division.spirit.vector.render.SpiritWingsModel;
import symbolics.division.spirit.vector.sfx.ClientSFX;
import symbolics.division.spirit.vector.sfx.EffectsManager;

public class SpiritVectorClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		EffectsManager.registerSFXRequestC2SCallback(ClientPlayNetworking::send);

		// spirit wings reg
		EntityModelLayerRegistry.registerModelLayer(SpiritWingsModel.LAYER, SpiritWingsModel::getTexturedModelData);
		LivingEntityFeatureRendererRegistrationCallback.EVENT.register(
			(entityType, entityRenderer, registrationHelper, context) -> {
				if (entityRenderer instanceof PlayerEntityRenderer) {
					ModelPart root = context.getPart(SpiritWingsModel.LAYER);
					registrationHelper.register(new SpiritWingsFeatureRenderer<>(entityRenderer, root));
				}
			}
		);

		// particles reg
		ClientSFX.registerAll();

		ClientTickEvents.START_CLIENT_TICK.register(InputHandler::tick);
	}
}