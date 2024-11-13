package br.com.alura.challenges.currency.converter.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PropertiesUtilTest {

    private PropertiesUtil propertiesUtil;

    @BeforeEach
    void setUp() {
        propertiesUtil = new PropertiesUtil();
    }

    @Test
    void testLoadValidJson() {
        String fileName = "validConfig";
        Class<TestConfig> clazz = TestConfig.class;
        TestConfig expectedConfig = new TestConfig("value1", 123);

        try (MockedStatic<Objects> mockedObjects = mockStatic(Objects.class);
             MockedStatic<Gson> mockedGson = mockStatic(Gson.class);
             MockedStatic<JsonReader> mockedJsonReader = mockStatic(JsonReader.class);
             MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {

            mockedObjects.when(() -> Objects.requireNonNull(any())).thenReturn(getClass().getClassLoader().getResource(fileName + ".json"));
            mockedGson.when(Gson::new).thenReturn(mock(Gson.class));
            mockedJsonReader.when(() -> new JsonReader(any(FileReader.class))).thenAnswer(invocation -> {
                JsonReader jsonReader = mock(JsonReader.class);
                doNothing().when(jsonReader).close();
                return jsonReader;
            });
            mockedFileReader.when(() -> new FileReader(anyString())).thenReturn(mock(FileReader.class));

            Gson gson = new Gson();
            when(gson.fromJson(any(JsonReader.class), eq(clazz))).thenReturn(expectedConfig);

            TestConfig actualConfig = propertiesUtil.load(fileName, clazz);

            assertNotNull(actualConfig);
            assertEquals(expectedConfig.getField1(), actualConfig.getField1());
            assertEquals(expectedConfig.getField2(), actualConfig.getField2());
        }
    }

    @Test
    void testLoadInvalidJson() {
        String fileName = "invalidConfig";
        Class<TestConfig> clazz = TestConfig.class;

        try (MockedStatic<Objects> mockedObjects = mockStatic(Objects.class);
             MockedStatic<Gson> mockedGson = mockStatic(Gson.class);
             MockedStatic<JsonReader> mockedJsonReader = mockStatic(JsonReader.class);
             MockedStatic<FileReader> mockedFileReader = mockStatic(FileReader.class)) {

            mockedObjects.when(() -> Objects.requireNonNull(any())).thenReturn(getClass().getClassLoader().getResource(fileName + ".json"));
            mockedGson.when(Gson::new).thenReturn(mock(Gson.class));
            mockedJsonReader.when(() -> new JsonReader(any(FileReader.class))).thenAnswer(invocation -> {
                JsonReader jsonReader = mock(JsonReader.class);
                doNothing().when(jsonReader).close();
                return jsonReader;
            });
            mockedFileReader.when(() -> new FileReader(anyString())).thenReturn(mock(FileReader.class));

            Gson gson = new Gson();
            when(gson.fromJson(any(JsonReader.class), eq(clazz))).thenThrow(new IOException());

            TestConfig actualConfig = propertiesUtil.load(fileName, clazz);

            assertNull(actualConfig);
        }
    }

    @Test
    void testLoadNonExistentJson() {
        String fileName = "nonExistentConfig";
        Class<TestConfig> clazz = TestConfig.class;

        try (MockedStatic<Objects> mockedObjects = mockStatic(Objects.class)) {
            mockedObjects.when(() -> Objects.requireNonNull(any())).thenThrow(new NullPointerException());

            TestConfig actualConfig = propertiesUtil.load(fileName, clazz);

            assertNull(actualConfig);
        }
    }

    static class TestConfig {
        private String field1;
        private int field2;

        public TestConfig(String field1, int field2) {
            this.field1 = field1;
            this.field2 = field2;
        }

        public String getField1() {
            return field1;
        }

        public int getField2() {
            return field2;
        }
    }
}