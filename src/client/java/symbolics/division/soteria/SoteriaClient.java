package symbolics.division.soteria;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import symbolics.division.soteria.item.SoterianLance;
import symbolics.division.soteria.network.PoiseSparkAttackC2S;
import symbolics.division.spirit_vector.logic.ISpiritVectorUser;

public class SoteriaClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        SoterianLance.hitCallback = (target, player, damage) -> {
            if (player instanceof ISpiritVectorUser user && user.spiritVector() != null) {
                ClientPlayNetworking.send(new PoiseSparkAttackC2S(damage, target.getId()));
            }
        };

        EntityRendererRegistry.register(SoterianEntities.POISE_SPARK, PoiseSparkRenderer::new);
    }
}