package br.com.lbenaducci.maquinavelha

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
@OpenAPIDefinition(info = Info(title = "Maquina Velha", version = "0.0.1"))
class MaquinaVelhaApplication

fun main(args: Array<String>) {
    runApplication<MaquinaVelhaApplication>(*args)
}
