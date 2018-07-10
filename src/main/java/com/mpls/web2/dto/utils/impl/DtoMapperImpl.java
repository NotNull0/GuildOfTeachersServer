package com.mpls.web2.dto.utils.impl;

import com.mpls.web2.dto.utils.DtoMapper;
import com.mpls.web2.dto.utils.annotations.Dto;
import com.mpls.web2.dto.utils.annotations.EnableMapper;
import org.apache.log4j.Logger;
import org.reflections.Reflections;

import javax.persistence.Entity;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toList;

@EnableMapper(dto = "com.mpls.web2.dto", model = "com.mpls.web2.model")
public class DtoMapperImpl implements DtoMapper {

    private static final Logger logger = Logger.getLogger(DtoMapperImpl.class);

    private Class classFromReflection;
    private EnableMapper enableMapper;
    private Reflections reflections = new Reflections();
    private Set<Class<?>> dto;
    private Set<Class<?>> model;

    {
        init();
    }

    public void init() {
        model = reflections.getTypesAnnotatedWith(Entity.class);
        dto = reflections.getTypesAnnotatedWith(Dto.class);
    }

    public Object parseFromDTOtoObject(Object dtoObject, Class... parsingClasses) {
        if (dtoObject == null)
            return null;
        try {
            final Object object = parsingClasses[0].newInstance();
            Arrays.stream(parsingClasses[0].getMethods())
                    .filter(method1 -> method1.getName().contains("set"))
                    .forEach(method1 -> {
                        try {
                            if (!method1.getParameterTypes()[0].equals(List.class) && (method1.getParameterTypes()[0].getSuperclass().equals(Enum.class)
                                    || method1.getParameterTypes()[0].equals(Enum.class)
                                    || method1.getParameterTypes()[0].equals(String.class)
                                    || method1.getParameterTypes()[0].equals(Character.class)
                                    || method1.getParameterTypes()[0].getSuperclass().equals(Number.class)
                                    || method1.getParameterTypes()[0].equals(LocalDate.class)
                                    || method1.getParameterTypes()[0].equals(Timestamp.class)
                                    || method1.getParameterTypes()[0].equals(LocalDateTime.class)
                                    || method1.getParameterTypes()[0].equals(Boolean.class))) {

                                Arrays.stream(dtoObject.getClass().getMethods())
                                        .filter(method -> method.getName().contains("get"))
                                        .filter(method -> method.getName().replace("get", "").equals(method1.getName().replace("set", "")))
                                        .forEach(method -> {

                                            try {
                                                if (method.invoke(dtoObject) != null)
                                                    method1.invoke(object, method.invoke(dtoObject));
                                            } catch (IllegalAccessException | InvocationTargetException e) {
                                                e.printStackTrace();
                                            }
                                        });
                            } else {
                                Arrays.stream(dtoObject.getClass().getMethods())
                                        .filter(method -> method.getName().contains("get"))
                                        .filter(method -> method.getName().replace("get", "").equals(method1.getName().replace("set", "")))
                                        .forEach(method -> {
                                            try {
                                                if (method1.getParameterTypes()[0].equals(List.class)) {
                                                    List a = (List) method.invoke(dtoObject);
                                                    if (a == null) {
                                                        a = new ArrayList();
                                                        method1.invoke(object, a);//todo
                                                    } else {
                                                        method1.invoke(object, a.stream().map(o -> (parseFromDTOtoObject(o, (Class) ((ParameterizedType) method1.getParameters()[0].getParameterizedType()).getActualTypeArguments()[0]))).collect(toList()));//todo

                                                    }
                                                } else {
                                                    if (method.invoke(dtoObject) != null)
                                                        method1.invoke(object, parseFromDTOtoObject(method.invoke(dtoObject), method1.getParameterTypes()[0]));//todo
                                                }
                                            } catch (IllegalAccessException | InvocationTargetException e) {
                                                e.printStackTrace();
                                            } catch (Exception e) {
                                                e.printStackTrace();
                                                try {
                                                    logger.info("found an exception");
                                                    logger.info(method1.getName());
                                                } catch (Exception ee) {
                                                    logger.info("sukabliadskepizdoujobishche");
                                                }
                                            }
                                        });
                            }
                        } catch (NullPointerException e) {
                            logger.info(method1.getParameterTypes()[0].getName());
                            logger.info(method1.getName());
                            logger.info(parsingClasses[0].getName());
                            e.printStackTrace();
//                            try {
//                                method1.invoke(object, new ArrayList());
//                            } catch (IllegalAccessException | InvocationTargetException e1) {
//                                e.printStackTrace();
//                            }
                        }
                    });
            return object;
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }


        return null;
    }

    private Object tempParse(Object dtoObject, Class... parsingClasses) {
        if (dtoObject == null)
            return null;
        try {
//            classFromReflection = (Class) new Reflections("com.mplus.web1.dto.utils").getTypesAnnotatedWith(EnableMapper.class).toArray()[0];
//            enableMapper = (EnableMapper) classFromReflection.getAnnotations()[0];
//            reflections = new Reflections(enableMapper.dto());
        } catch (Exception e) {
            e.printStackTrace();
            return null;

        }

        try {

            if (Arrays.stream(dtoObject.getClass().getAnnotations()).filter(annotation -> annotation instanceof Entity).count() != 0) {

                if (dto.stream().filter(aClass -> dtoObject.getClass().getName().contains(aClass.getName().replace("Dto", ""))).count() != 0 || parsingClasses.length != 0) {

                    final Object parsedObject = Arrays.stream(parsingClasses).findFirst().orElse(model.stream().filter(aClass -> dtoObject.getClass().getName().contains(aClass.getName().replace("Dto", ""))).findFirst().get()).newInstance();


                    Method[] methods = dtoObject.getClass().getMethods();

                    parser(dtoObject, parsedObject, methods);

                    return parsedObject;
                }
            } else if (Arrays.stream(dtoObject.getClass().getAnnotations()).filter(annotation -> annotation instanceof Dto).count() != 0) {

                final Object parsedObject = Arrays.stream(parsingClasses).findFirst().orElse(dto.stream().filter(aClass -> dtoObject.getClass().getName().contains(aClass.getName())).findFirst().get()).newInstance();

                Method[] methods = dtoObject.getClass().getMethods();


                parser(dtoObject, parsedObject, methods);

                return parsedObject;
            }


            return null;

        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void parser(Object dtoObject, Object parsedObject, Method[] methods) {
        if (dtoObject == null)
            return;
        Arrays.stream(parsedObject.getClass().getMethods()).filter(method -> method.getName().contains("set")).forEach(
                method -> {
                    if ((method.getParameterTypes()[0].getSuperclass().equals(Enum.class)
                            || method.getParameterTypes()[0].equals(Enum.class)
                            || method.getParameterTypes()[0].equals(String.class)
                            || method.getParameterTypes()[0].equals(Character.class)
                            || method.getParameterTypes()[0].getSuperclass().equals(Number.class)
                            || method.getParameterTypes()[0].equals(LocalDate.class)
                            || method.getParameterTypes()[0].equals(Timestamp.class)
                            || method.getParameterTypes()[0].equals(LocalDateTime.class))) {


                        if (Arrays.stream(methods).filter(method1 -> method.getName().equals(method1.getName())).count() != 0) {

                            Method temp = Arrays.stream(methods).filter(method1 -> method1.getName().contains("get")).filter(method1 -> method.getName().replace("set", "").equals(method1.getName().replace("get", ""))).findFirst().get();

                            try {
                                method.invoke(parsedObject, temp.invoke(dtoObject));
                            } catch (IllegalAccessException | InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
        );
    }
}
