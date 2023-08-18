package com.metamorphosis.kafkajsonsolution.serializers;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.Decoder;
import org.apache.avro.io.DecoderFactory;
import org.apache.avro.specific.SpecificDatumReader;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Deserializer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;

public class AvroDeserializer implements Deserializer<schemas.Course> {
    @Override
    public schemas.Course deserialize(String topic, byte[] data) {

        try {
            if (data != null) {
                DatumReader<schemas.Course> reader = new SpecificDatumReader<>(schemas.Course.getClassSchema());
                Decoder decoder = DecoderFactory.get().binaryDecoder(data, null);
                return reader.read(null, decoder);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
