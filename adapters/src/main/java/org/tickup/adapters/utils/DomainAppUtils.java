package org.tickup.adapters.utils;//package com.itcentrex.adapters.utils;
//
//import com.fasterxml.jackson.core.JsonFactory;
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//import java.util.HashMap;
//import java.util.Iterator;
//import java.util.Map;
//import java.util.regex.Matcher;
//import java.util.regex.Pattern;
//
//public class DomainAppUtils {
//    public static Map<String, Object> parse(String json) throws JsonProcessingException {
//        Map<String, Object> objectMap = new HashMap<>();
//        JsonFactory factory = new JsonFactory();
//
//        ObjectMapper mapper = new ObjectMapper(factory);
//        JsonNode rootNode = mapper.readTree(json);
//
//        Iterator<Map.Entry<String, JsonNode>> fieldsIterator = rootNode.fields();
//        while (fieldsIterator.hasNext()) {
//
//            Map.Entry<String, JsonNode> field = fieldsIterator.next();
//            objectMap.put(field.getKey(), field.getValue());
//            System.out.println("Key: " + field.getKey() + "\tValue:" + field.getValue());
//        }
//
//        System.out.println("new map: " + objectMap);
//        return objectMap;
//    }
//
//    public static String getMessage(String input) {
//
//        Pattern pattern = Pattern.compile("\"message\":\"([^\"]+)\"");
//        Matcher matcher = pattern.matcher(input);
//
//        if (matcher.find()) {
//            String message = matcher.group(1);
//            input = message;
//            System.out.println("Message : " + message);
//        } else {
//            System.out.println("Message non trouvé !");
//        }
//
//        return input;
//    }
//}
