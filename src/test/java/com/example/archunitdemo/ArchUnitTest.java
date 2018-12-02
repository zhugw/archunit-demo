package com.example.archunitdemo;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.junit.ArchUnitRunner;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RestController;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.noClasses;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static com.tngtech.archunit.library.GeneralCodingRules.ACCESS_STANDARD_STREAMS;

/**
 * Created by zhuguowei on 2018-12-01.
 */
@RunWith(ArchUnitRunner.class)
@AnalyzeClasses(packages = "com.example.archunitdemo")
public class ArchUnitTest {

    @ArchTest
    public static final ArchRule services_should_not_depend_on_controllers =
            noClasses().that().resideInAPackage("..service..")
                    .should().dependOnClassesThat().resideInAPackage("..controller..");


    @ArchTest
    private void no_access_to_standard_streams_as_method(JavaClasses classes) {
        noClasses().should(ACCESS_STANDARD_STREAMS).check(classes);
    }

    @ArchTest
    public static final ArchRule DAOs_must_reside_in_a_dao_package =
            classes().that().haveNameMatching(".*Dao").should().resideInAPackage("..dao..")
                    .as("DAOs should reside in a package '..dao..'");

    @ArchTest
    public static final ArchRule layer_dependencies_are_respected = layeredArchitecture()

            .layer("Controllers").definedBy("com.example.archunitdemo.controller..")
            .layer("Services").definedBy("com.example.archunitdemo.service..")
            .layer("Dao").definedBy("com.example.archunitdemo.dao..")

            .whereLayer("Controllers").mayNotBeAccessedByAnyLayer()
            .whereLayer("Services").mayOnlyBeAccessedByLayers("Controllers")
            .whereLayer("Dao").mayOnlyBeAccessedByLayers("Services");

    @ArchTest
    public static ArchRule controllers_should_be_suffixed =
            classes()
                    .that().resideInAPackage("..controller..")
                    .or().areAnnotatedWith(RestController.class)
                    .should().haveSimpleNameEndingWith("Controller");



}
