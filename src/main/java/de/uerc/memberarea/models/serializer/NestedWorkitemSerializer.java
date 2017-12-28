package de.uerc.memberarea.models.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.uerc.memberarea.models.workitem.Workitem;

public class NestedWorkitemSerializer extends JsonSerializer<Workitem> {

    @Override
    public void serialize(Workitem value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeEndObject();
    }

}
