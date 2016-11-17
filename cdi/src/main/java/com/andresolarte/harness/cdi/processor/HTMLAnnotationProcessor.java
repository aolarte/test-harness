package com.andresolarte.harness.cdi.processor;

import com.andresolarte.harness.cdi.HTML;
import com.andresolarte.harness.cdi.interceptor.HTMLPrettify;
import org.apache.deltaspike.core.api.literal.NamedLiteral;
import org.apache.deltaspike.core.util.metadata.builder.AnnotatedTypeBuilder;

import javax.enterprise.event.Observes;
import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.inject.spi.Extension;
import javax.enterprise.inject.spi.ProcessAnnotatedType;
import javax.enterprise.inject.spi.WithAnnotations;
import javax.enterprise.util.AnnotationLiteral;


public class HTMLAnnotationProcessor implements Extension {

    <T> void processAnnotatedType(@Observes @WithAnnotations({HTML.class}) ProcessAnnotatedType<T> pat) {

        AnnotatedTypeBuilder annotatedTypeBuilder = new AnnotatedTypeBuilder()
                .readFromType(pat.getAnnotatedType())
                .addToClass(new AnnotationLiteral<HTMLPrettify>() {
                });

        AnnotatedType<T> type= annotatedTypeBuilder.create();

        pat.setAnnotatedType(type);

    }
}
