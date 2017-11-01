package de.uerc.memberarea.json;

import java.io.IOException;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;

public class NestedSerializer extends JsonSerializer<Object> {

    @Override
    public void serialize(Object object, JsonGenerator jsonGenerator, SerializerProvider serializerProvider)
        throws IOException, JsonProcessingException {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setConfig(mapper.getSerializationConfig().withView(View.Nested.class));

        jsonGenerator.setCodec(mapper);
        jsonGenerator.writeObject(object);

    }

}
