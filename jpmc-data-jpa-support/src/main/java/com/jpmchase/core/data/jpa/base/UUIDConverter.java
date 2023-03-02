//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.jpmchase.core.data.jpa.base;

import java.util.UUID;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class UUIDConverter implements AttributeConverter<String, UUID> {
    public UUIDConverter() {
    }

    public UUID convertToDatabaseColumn(String suuid) {
        return suuid == null ? null : UUID.fromString(suuid);
    }

    public String convertToEntityAttribute(UUID uuid) {
        return uuid == null ? null : uuid.toString();
    }
}
