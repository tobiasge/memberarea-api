package de.uerc.memberarea.models.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import de.uerc.memberarea.models.users.ClubMember;

public class NestedClubMemberSerializer extends JsonSerializer<ClubMember> {

    @Override
    public void serialize(ClubMember value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("displayName", value.getDisplayName());
        gen.writeEndObject();
    }

}
