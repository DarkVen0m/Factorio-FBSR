package com.demod.fbsr;

import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.OptionalInt;
import java.util.function.BiFunction;
import java.util.function.Function;

import org.json.JSONObject;

import com.demod.factorio.Utils;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableList.Builder;

public final class BSUtils {
	public static Direction direction(JSONObject json, String key) {
		if (!json.has(key)) {
			return Direction.NORTH;
		}
		return Direction.values()[json.getInt(key) / 2];
	}

	public static <T> List<T> list(JSONObject json, String key, BiFunction<JSONObject, String, T> factory) {
		if (!json.has(key)) {
			return ImmutableList.of();
		}
		Builder<T> builder = ImmutableList.builder();
		Utils.forEach(json.getJSONArray(key), (JSONObject j) -> builder.add(factory.apply(j, key)));
		return builder.build();
	}

	public static <T> List<T> list(JSONObject json, String key, Function<JSONObject, T> factory) {
		if (!json.has(key)) {
			return ImmutableList.of();
		}
		Builder<T> builder = ImmutableList.builder();
		Utils.forEach(json.getJSONArray(key), (JSONObject j) -> builder.add(factory.apply(j)));
		return builder.build();
	}

	public static <T> Optional<T> opt(JSONObject json, String key, BiFunction<JSONObject, String, T> factory) {
		if (!json.has(key)) {
			return Optional.empty();
		}
		return Optional.of(factory.apply(json, key));
	}

	public static <T> Optional<T> opt(JSONObject json, String key, Function<JSONObject, T> factory) {
		if (!json.has(key)) {
			return Optional.empty();
		}
		return Optional.of(factory.apply(json.getJSONObject(key)));
	}

	public static Optional<Boolean> optBool(JSONObject json, String key) {
		if (!json.has(key)) {
			return Optional.empty();
		}
		return Optional.of(json.getBoolean(key));
	}

	public static OptionalDouble optDouble(JSONObject json, String key) {
		if (!json.has(key)) {
			return OptionalDouble.empty();
		}
		return OptionalDouble.of(json.getDouble(key));
	}

	public static OptionalInt optInt(JSONObject json, String key) {
		if (!json.has(key)) {
			return OptionalInt.empty();
		}
		return OptionalInt.of(json.getInt(key));
	}

	public static <T> Optional<List<T>> optList(JSONObject json, String key, Function<JSONObject, T> factory) {
		if (!json.has(key)) {
			return Optional.empty();
		}
		Builder<T> builder = ImmutableList.builder();
		Utils.forEach(json.getJSONArray(key), (JSONObject j) -> builder.add(factory.apply(j)));
		return Optional.of(builder.build());
	}

	public static Optional<ItemQuality> optQuality(JSONObject json, String key) {
		return BSUtils.opt(json, key, (j, k) -> ItemQuality.valueOf(j.getString(k).toUpperCase()));
	}

	public static Optional<String> optString(JSONObject json, String key) {
		if (!json.has(key)) {
			return Optional.empty();
		}
		return Optional.of(json.getString(key));
	}

	public static ItemQuality quality(JSONObject json, String key) {
		return ItemQuality.valueOf(json.getString(key).toUpperCase());
	}
}
