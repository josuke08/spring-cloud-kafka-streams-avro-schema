package com.metamorphosis.kafkajsonsolution.serializers;

import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.Serializer;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class AvroSerializer implements Serializer<schemas.Course> {
    @Override
    public byte[] serialize(String topic, schemas.Course data) {

        byte[] arr = new byte[100000];
        try {
            try (ByteArrayOutputStream outputStream = new ByteArrayOutputStream()) {
                BinaryEncoder binaryEncoder = EncoderFactory.get().binaryEncoder(outputStream, null);
                GenericDatumWriter<schemas.Course> writer = new GenericDatumWriter<>(data.getSchema());
                writer.write(data, binaryEncoder);
                binaryEncoder.flush();
                arr = outputStream.toByteArray();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return arr;
    }
}
