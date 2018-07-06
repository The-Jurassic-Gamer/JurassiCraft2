package org.jurassicraft.server.json.dinosaur.objects;

import com.google.gson.*;
import lombok.AccessLevel;
import lombok.Data;
import lombok.experimental.FieldDefaults;
import net.minecraft.util.JsonUtils;

import java.lang.reflect.Type;

@Data
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class DinosaurStatistics {

    AdultBabyValue speed;
    AdultBabyValue health;
    AdultBabyValue strength;
    AdultBabyValue sizeX;
    AdultBabyValue sizeY;
    AdultBabyValue eyeHeight;
    AdultBabyValue scale;
    int jumpHeight;
    double attackSpeed;
    int itemStorage;

    public static class JsonHandler implements JsonDeserializer<DinosaurStatistics>, JsonSerializer<DinosaurStatistics> {

        @Override
        public DinosaurStatistics deserialize(JsonElement element, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
            if(!element.isJsonObject()) {
                throw new JsonParseException("Expected Json Object, found " + JsonUtils.toString(element));
            }
            JsonObject json = element.getAsJsonObject();
            return new DinosaurStatistics(
                    context.deserialize(JsonUtils.getJsonObject(json, "speed"), AdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "health"), AdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "strength"), AdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "size_x"), AdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "size_y"), AdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "eye_height"), AdultBabyValue.class),
                    context.deserialize(JsonUtils.getJsonObject(json, "scale"), AdultBabyValue.class),
                    JsonUtils.getInt(json, "jump_height"),
                    JsonUtils.getFloat(json, "attack_speed"),
                    JsonUtils.getInt(json, "item_storage")
            );
        }

        @Override
        public JsonElement serialize(DinosaurStatistics src, Type typeOfSrc, JsonSerializationContext context) {
            JsonObject json = new JsonObject();
            json.add("speed", context.serialize(src.getSpeed()));
            json.add("health", context.serialize(src.getHealth()));
            json.add("strength", context.serialize(src.getStrength()));
            json.add("size_x", context.serialize(src.getSizeX()));
            json.add("size_y", context.serialize(src.getSizeY()));
            json.add("eye_height", context.serialize(src.getEyeHeight()));
            json.add("scale", context.serialize(src.getScale()));
            json.addProperty("jump_height", src.getJumpHeight());
            json.addProperty("attack_speed", src.getAttackSpeed());
            json.addProperty("item_storage", src.getItemStorage());
            return json;
        }
    }
}